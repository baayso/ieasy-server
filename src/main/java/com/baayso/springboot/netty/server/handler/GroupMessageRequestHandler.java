package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.GroupMessageRequestPacket;
import com.baayso.springboot.netty.protocol.response.GroupMessageResponsePacket;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket request) {
        // 1.拿到 groupId 构造群聊消息的响应
        Long groupId = request.getToGroupId();
        GroupMessageResponsePacket response = new GroupMessageResponsePacket();
        response.setFromGroupId(groupId);
        response.setMessage(request.getMessage());
        response.setFromUser(SessionUtils.getSession(ctx.channel()));

        // 2.拿到群聊对应的 channelGroup，写给每个客户端
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.writeAndFlush(response);
    }

}
