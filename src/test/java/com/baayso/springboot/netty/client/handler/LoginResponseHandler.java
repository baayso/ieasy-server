package com.baayso.springboot.netty.client.handler;

import java.util.Date;
import java.util.Scanner;

import com.baayso.commons.sequence.Sequence;
import com.baayso.springboot.netty.protocol.request.LoginRequestPacket;
import com.baayso.springboot.netty.protocol.request.MessageRequestPacket;
import com.baayso.springboot.netty.protocol.response.LoginResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private final Sequence sequence = new Sequence(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket request = new LoginRequestPacket();
        request.setUserId(this.sequence.nextId());
        request.setUsername("baayso");
        request.setPassword("baayso");

        // 写数据
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端连接被关闭");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket response) {
        if (response.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功，启动控制台线程......");

            // LoginUtils.markAsLogin(ctx.channel());

            startConsoleThread(ctx);
        }
        else {
            System.out.println(new Date() + ": 客户端登录失败，原因: " + response.getReason());
        }
    }

    private static void startConsoleThread(ChannelHandlerContext ctx) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("请输入消息发送至服务端: ");
                Scanner sc = new Scanner(System.in);
                String line = sc.nextLine();

                ctx.writeAndFlush(new MessageRequestPacket(line));
            }
        }).start();
    }

}
