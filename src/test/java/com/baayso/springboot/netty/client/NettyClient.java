package com.baayso.springboot.netty.client;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.common.utils.ThreadPool;
import com.baayso.springboot.netty.client.console.ConsoleCommandManager;
import com.baayso.springboot.netty.client.console.LoginConsoleCommand;
import com.baayso.springboot.netty.client.handler.ClientChannelInitializer;
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

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(ThreadPool.AVAILABLE_PROCESSORS);

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
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                }
                else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

}
