package com.baayso.springboot.netty.protocol.request;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestPacket extends Packet {

    private Long   toUserId; // 要发送给哪个用户
    private String message;  // 具体消息内容

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(Long toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

}
