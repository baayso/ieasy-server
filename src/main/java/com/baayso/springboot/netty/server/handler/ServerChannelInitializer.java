package com.baayso.springboot.netty.server.handler;

import org.springframework.stereotype.Component;

import com.baayso.commons.sequence.Sequence;
import com.baayso.springboot.common.utils.ThreadPool;
import com.baayso.springboot.netty.codec.PacketCodecHandler;
import com.baayso.springboot.netty.codec.Spliter;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

@Component
public class ServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private final EventExecutorGroup group = new DefaultEventLoopGroup(ThreadPool.AVAILABLE_PROCESSORS);

    private final Sequence         sequence;
    private final AuthHandler      authHandler;
    private final IMServiceHandler imServiceHandler;

    public ServerChannelInitializer(Sequence sequence, AuthHandler authHandler, IMServiceHandler imServiceHandler) {
        this.sequence = sequence;
        this.authHandler = authHandler;
        this.imServiceHandler = imServiceHandler;
    }

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline()
                .addLast(new Spliter())
                .addLast(PacketCodecHandler.INSTANCE)
                .addLast(this.group, new LoginRequestHandler(this.sequence))
                .addLast(this.group, this.authHandler)
                .addLast(this.group, this.imServiceHandler);
    }

}
