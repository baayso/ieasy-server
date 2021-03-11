package com.baayso.springboot.netty.server.handler;

import org.springframework.stereotype.Component;

import com.baayso.commons.sequence.Sequence;
import com.baayso.springboot.netty.codec.PacketCodecHandler;
import com.baayso.springboot.netty.codec.Spliter;
import com.baayso.springboot.netty.common.handler.IMIdleStateHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

@Component
public class ServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private static final EventExecutorGroup EXECUTOR_GROUP = new UnorderedThreadPoolEventExecutor(NettyRuntime.availableProcessors());

    private final Sequence         sequence;
    private final AuthHandler      authHandler;
    private final IMServiceHandler imServiceHandler;

    public ServerChannelInitializer(Sequence sequence,
                                    AuthHandler authHandler,
                                    IMServiceHandler imServiceHandler) {
        this.sequence = sequence;
        this.authHandler = authHandler;
        this.imServiceHandler = imServiceHandler;
    }

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline()
                .addLast(new IMIdleStateHandler())
                .addLast(new Spliter())
                .addLast(PacketCodecHandler.INSTANCE)
                .addLast(HeartBeatRequestHandler.INSTANCE)
                .addLast(EXECUTOR_GROUP, new LoginRequestHandler(this.sequence))
                .addLast(EXECUTOR_GROUP, this.authHandler)
                .addLast(EXECUTOR_GROUP, this.imServiceHandler);
    }

}
