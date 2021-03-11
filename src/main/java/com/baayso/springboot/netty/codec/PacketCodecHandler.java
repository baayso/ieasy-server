package com.baayso.springboot.netty.codec;

import java.util.List;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        out.add(PacketCodec.INSTANCE.decode(msg));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) {
        // ioBuffer()方法会返回适配IO读写相关的内存，它会尽可能创建一个直接内存
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(byteBuf, msg);
        out.add(byteBuf);
    }

}
