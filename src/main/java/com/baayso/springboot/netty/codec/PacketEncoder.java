package com.baayso.springboot.netty.codec;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器。
 *
 * @author ChenFangjie (2021/2/25 19:00)
 * @since 4.0.0
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, msg);
    }

}
