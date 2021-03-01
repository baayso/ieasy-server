package com.baayso.springboot.netty.client.handler;

import com.baayso.springboot.netty.protocol.response.ListGroupMembersResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) {
        System.out.println("群【" + responsePacket.getGroupId() + "】内包含的用户有：" + responsePacket.getSessions());
    }

}
