package com.baayso.springboot.netty.client.handler;

import com.baayso.springboot.netty.protocol.response.JoinGroupResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket response) {
        if (response.isSuccess()) {
            System.out.println("加入群【" + response.getGroupId() + "】成功！");
        }
        else {
            System.err.println("加入群【" + response.getGroupId() + "】失败，原因：" + response.getReason());
        }
    }

}
