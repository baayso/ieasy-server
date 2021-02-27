package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.MessageRequestPacket;
import com.baayso.springboot.netty.protocol.response.MessageResponsePacket;
import com.baayso.springboot.netty.session.Session;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket request) {
        // 拿到消息接收方的channel
        Long toUserId = request.getToUserId();
        Channel toUserChannel = SessionUtils.getChannel(toUserId);

        // 将消息发送给消息接收方
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            // 拿到消息发送方的会话信息
            Session session = SessionUtils.getSession(ctx.channel());

            // 通过消息发送方的会话信息构建要发送的消息
            MessageResponsePacket response = new MessageResponsePacket();
            response.setFromUserId(session.getUserId());
            response.setFromUsername(session.getUsername());
            response.setMessage(request.getMessage());

            toUserChannel.writeAndFlush(response);
        }
        else {
            MessageResponsePacket response = new MessageResponsePacket();
            response.setFromUserId(100L);
            response.setFromUsername("system");
            response.setMessage("对方不在线，发送失败！");

            ctx.writeAndFlush(response);

            log.info("【{}】不在线，发送失败！", toUserId);
        }
    }

}
