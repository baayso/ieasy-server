package com.baayso.springboot.netty.client.handler;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.netty.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket response) {
        Long fromUserId = response.getFromUserId();
        String fromUsername = response.getFromUsername();
        String message = response.getMessage();

        String now = DateTimeUtils.nowDateTimeSeparator();
        System.out.println(String.format("%s %s:%s -> %s", now, fromUserId, fromUsername, message));
    }

}
