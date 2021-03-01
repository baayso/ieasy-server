package com.baayso.springboot.netty.protocol.response;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponsePacket extends Packet {

    private Long   fromUserId;   // 发送消息的用户标识
    private String fromUsername; // 发送消息的用户名
    private String message;      // 消息内容

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

}
