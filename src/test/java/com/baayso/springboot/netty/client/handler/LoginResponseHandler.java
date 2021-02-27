package com.baayso.springboot.netty.client.handler;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.netty.protocol.response.LoginResponsePacket;
import com.baayso.springboot.netty.session.Session;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(DateTimeUtils.nowDateTimeSeparator() + "：客户端连接被关闭！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket response) {
        Long userId = response.getUserId();
        String username = response.getUsername();
        String now = DateTimeUtils.nowDateTimeSeparator();

        if (response.isSuccess()) {
            Session session = new Session(userId, username);
            SessionUtils.bind(session, ctx.channel());

            System.out.println(String.format("%s：【%s】登录成功，userId为：%s", now, username, userId));
        }
        else {
            System.out.println(String.format("%s：【%s】登录失败，原因：%s", now, username, response.getReason()));
        }
    }

}
