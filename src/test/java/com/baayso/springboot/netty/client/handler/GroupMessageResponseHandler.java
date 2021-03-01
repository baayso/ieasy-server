package com.baayso.springboot.netty.client.handler;

import com.baayso.springboot.netty.protocol.response.GroupMessageResponsePacket;
import com.baayso.springboot.netty.session.Session;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket response) {
        Long fromGroupId = response.getFromGroupId();
        Session fromUser = response.getFromUser();

        System.out.println(String.format("收到群【%s】内【%s】发来的消息：%s", fromGroupId, fromUser, response.getMessage()));
    }

}
