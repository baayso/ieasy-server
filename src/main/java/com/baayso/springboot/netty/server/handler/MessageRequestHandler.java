package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.MessageRequestPacket;
import com.baayso.springboot.netty.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket request) {
        MessageResponsePacket response = new MessageResponsePacket();
        response.setMessage("服务端回复【" + request.getMessage() + "】");

        log.info("收到客户端消息: {}", request.getMessage());

        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();

        super.channelReadComplete(ctx);
    }

}
