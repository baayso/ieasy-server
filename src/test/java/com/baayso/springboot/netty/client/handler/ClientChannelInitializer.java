package com.baayso.springboot.netty.client.handler;

import com.baayso.springboot.netty.codec.PacketDecoder;
import com.baayso.springboot.netty.codec.PacketEncoder;
import com.baayso.springboot.netty.codec.Spliter;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) {

        // out bound
        ch.pipeline()
                .addLast(new PacketEncoder());

        // in bound
        ch.pipeline()
                .addLast(new Spliter())
                .addLast(new PacketDecoder())
                .addLast(new LoginResponseHandler())
                .addLast(new MessageResponseHandler());
    }

}
