package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.LogoutRequestPacket;
import com.baayso.springboot.netty.protocol.response.LogoutResponsePacket;
import com.baayso.springboot.netty.session.Session;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket request) {
        Channel channel = ctx.channel();

        Session session = SessionUtils.getSession(channel);
        log.info("【{}】主动下线（登出）", session.getUsername());

        SessionUtils.unbind(channel);

        LogoutResponsePacket response = new LogoutResponsePacket();
        response.setSuccess(true);

        ctx.writeAndFlush(response);
    }

}
