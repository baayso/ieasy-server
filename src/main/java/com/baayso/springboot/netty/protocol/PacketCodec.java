package com.baayso.springboot.netty.protocol;

import java.util.HashMap;
import java.util.Map;

import com.baayso.springboot.netty.protocol.command.Command;
import com.baayso.springboot.netty.protocol.request.CreateGroupRequestPacket;
import com.baayso.springboot.netty.protocol.request.GroupMessageRequestPacket;
import com.baayso.springboot.netty.protocol.request.JoinGroupRequestPacket;
import com.baayso.springboot.netty.protocol.request.ListGroupMembersRequestPacket;
import com.baayso.springboot.netty.protocol.request.LoginRequestPacket;
import com.baayso.springboot.netty.protocol.request.LogoutRequestPacket;
import com.baayso.springboot.netty.protocol.request.MessageRequestPacket;
import com.baayso.springboot.netty.protocol.request.QuitGroupRequestPacket;
import com.baayso.springboot.netty.protocol.response.CreateGroupResponsePacket;
import com.baayso.springboot.netty.protocol.response.GroupMessageResponsePacket;
import com.baayso.springboot.netty.protocol.response.JoinGroupResponsePacket;
import com.baayso.springboot.netty.protocol.response.ListGroupMembersResponsePacket;
import com.baayso.springboot.netty.protocol.response.LoginResponsePacket;
import com.baayso.springboot.netty.protocol.response.LogoutResponsePacket;
import com.baayso.springboot.netty.protocol.response.MessageResponsePacket;
import com.baayso.springboot.netty.protocol.response.QuitGroupResponsePacket;
import com.baayso.springboot.netty.serialize.Serializer;
import com.baayso.springboot.netty.serialize.impl.JSONSerializer;

import io.netty.buffer.ByteBuf;

/**
 * 通信协议：
 * <pre>
 * +--------------+---------+-------------------------+---------+-------------+----------------+
 * | Magic number | Version | Serialization algorithm | Command | Data length |      Data      |
 * |   4 bytes    | 1 byte  |         1 byte          | 1 byte  |   4 bytes   |     N bytes    |
 * +--------------+---------+-------------------------+---------+-------------+----------------+
 * </pre>
 */
public class PacketCodec {

    public static final PacketCodec INSTANCE = new PacketCodec();

    public static final int MAGIC_NUMBER = 0x19491001;

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer>              serializerMap;


    private PacketCodec() {
        this.packetTypeMap = new HashMap<>();
        this.packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        this.packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        this.packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        this.packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        this.packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        this.packetTypeMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        this.packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        this.packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        this.packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        this.packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        this.packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        this.packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        this.packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        this.packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        this.packetTypeMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        this.packetTypeMap.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);

        this.serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        this.serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = this.getRequestType(command);
        Serializer serializer = this.getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return this.serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return this.packetTypeMap.get(command);
    }

}
