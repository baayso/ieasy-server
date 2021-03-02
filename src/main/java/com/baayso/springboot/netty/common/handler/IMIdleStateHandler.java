package com.baayso.springboot.netty.common.handler;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 60;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        ctx.channel().close();

        log.info("{} 秒内未读到数据，关闭连接！", READER_IDLE_TIME);
    }

}
