package com.baayso.springboot.netty.client.handler;

import java.util.concurrent.TimeUnit;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.netty.protocol.request.HeartBeatRequestPacket;
import com.baayso.springboot.netty.protocol.response.HeartBeatResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatTimerHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    private static final int HEARTBEAT_INTERVAL = 20;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket msg) {
        System.out.println(DateTimeUtils.nowDateTimeSeparator() + ": 接收到服务端回复的心跳包！");
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());

                System.out.println(DateTimeUtils.nowDateTimeSeparator() + ": 向服务端发送心跳包！");

                this.scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

}
