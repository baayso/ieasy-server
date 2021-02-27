package com.baayso.springboot.netty.client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.common.utils.ThreadPool;
import com.baayso.springboot.netty.client.handler.ClientChannelInitializer;
import com.baayso.springboot.netty.protocol.request.LoginRequestPacket;
import com.baayso.springboot.netty.protocol.request.MessageRequestPacket;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    private static final String HOST      = "127.0.0.1";
    private static final int    PORT      = 8000;
    private static final int    MAX_RETRY = 5;

    private static final ExecutorService THREAD_POOL = ThreadPool.newThreadPool();

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(DateTimeUtils.nowDateTimeSeparator() + "：连接成功，启动控制台线程……");

                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            }
            else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            }
            else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(String.format("%s：连接失败，第%d次重连……", DateTimeUtils.nowDateTimeSeparator(), order));

                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);

        THREAD_POOL.execute(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    System.out.println("请输入用户名：");
                    String username = sc.nextLine();
                    System.out.println("请输入密码：");
                    String password = sc.nextLine();

                    LoginRequestPacket request = new LoginRequestPacket();
                    request.setUsername(username);
                    request.setPassword(password);

                    channel.writeAndFlush(request);

                    waitForLoginResponse();
                }
                else {
                    Long toUserId = Long.valueOf(sc.next());
                    String message = sc.next();

                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        });
    }

    private static void waitForLoginResponse() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        }
        catch (InterruptedException e) {
            // ignore
        }
    }

}
