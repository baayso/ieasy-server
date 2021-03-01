package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.QuitGroupRequestPacket;
import com.baayso.springboot.netty.protocol.response.QuitGroupResponsePacket;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket request) {
        // 1.获取群对应的 channelGroup，然后将当前用户的 channel 移除
        Long groupId = request.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2.构建退群响应并发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.writeAndFlush(responsePacket);
    }

}
