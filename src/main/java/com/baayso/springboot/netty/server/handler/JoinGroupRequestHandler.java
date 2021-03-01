package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.JoinGroupRequestPacket;
import com.baayso.springboot.netty.protocol.response.JoinGroupResponsePacket;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket request) {
        // 1.获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        Long groupId = request.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 2.构造加群响应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.writeAndFlush(responsePacket);
    }

}
