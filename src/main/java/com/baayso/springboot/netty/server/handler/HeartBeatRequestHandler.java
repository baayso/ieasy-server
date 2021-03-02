package com.baayso.springboot.netty.server.handler;

import java.net.InetSocketAddress;

import com.baayso.springboot.netty.protocol.request.HeartBeatRequestPacket;
import com.baayso.springboot.netty.protocol.response.HeartBeatResponsePacket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket request) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = socketAddress.getAddress().getHostAddress();
        int port = socketAddress.getPort();

        log.info("接收到客户端[{}:{}]的心跳包！", ip, port);

        ctx.writeAndFlush(new HeartBeatResponsePacket());

        log.info("向客户端[{}:{}]回复心跳包！", ip, port);
    }

}
