package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.common.utils.ThreadPool;
import com.baayso.springboot.netty.codec.PacketDecoder;
import com.baayso.springboot.netty.codec.PacketEncoder;
import com.baayso.springboot.netty.codec.Spliter;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

public class ServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private final EventExecutorGroup group = new DefaultEventLoopGroup(ThreadPool.AVAILABLE_PROCESSORS);

    @Override
    protected void initChannel(NioSocketChannel ch) {

        // out bound
        ch.pipeline()
                .addLast(new PacketEncoder());

        // in bound
        ch.pipeline()
                .addLast(new Spliter())
                .addLast(new PacketDecoder())
                .addLast(this.group, new LoginRequestHandler())
                .addLast(this.group, new AuthHandler())
                .addLast(new MessageRequestHandler());
    }

}
