package com.baayso.springboot.netty.client.handler;

import java.util.Date;

import com.baayso.springboot.netty.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket response) {
        System.out.println(new Date() + ": 收到服务端的消息: " + response.getMessage());
    }

}
