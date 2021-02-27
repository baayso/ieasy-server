package com.baayso.springboot.netty.client.handler;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.netty.protocol.response.LogoutResponsePacket;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket response) {
        SessionUtils.unbind(ctx.channel());

        ctx.channel().close();

        System.out.println(DateTimeUtils.nowDateTimeSeparator() + "：登出！");

        System.exit(0);
    }

}
