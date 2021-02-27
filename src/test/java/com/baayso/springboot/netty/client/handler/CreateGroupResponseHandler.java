package com.baayso.springboot.netty.client.handler;

import com.baayso.springboot.netty.protocol.response.CreateGroupResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket response) {
        System.out.print("群创建成功，群ID为：" + response.getGroupId() + "，");
        System.out.println("群里包含的用户有：" + response.getUsernames());
    }

}
