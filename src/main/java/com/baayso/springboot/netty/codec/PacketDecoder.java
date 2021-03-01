package com.baayso.springboot.netty.codec;

import java.util.List;

import com.baayso.springboot.netty.protocol.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 解码器。
 *
 * @author ChenFangjie (2021/2/25 18:58)
 * @since 4.0.0
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode(in));
    }

}
