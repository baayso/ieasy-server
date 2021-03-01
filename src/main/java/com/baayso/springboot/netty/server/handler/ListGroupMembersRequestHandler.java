package com.baayso.springboot.netty.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.baayso.springboot.netty.protocol.request.ListGroupMembersRequestPacket;
import com.baayso.springboot.netty.protocol.response.ListGroupMembersResponsePacket;
import com.baayso.springboot.netty.session.Session;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket request) {
        // 1.获取群的 ChannelGroup
        Long groupId = request.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);

        // 2.遍历群成员的 channel 以获取对应的 session，构造群成员的信息
        List<Session> sessions = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtils.getSession(channel);
            sessions.add(session);
        }

        // 3.构建群成员列表响应并写回到客户端
        ListGroupMembersResponsePacket response = new ListGroupMembersResponsePacket();
        response.setGroupId(groupId);
        response.setSessions(sessions);

        ctx.writeAndFlush(response);
    }

}
