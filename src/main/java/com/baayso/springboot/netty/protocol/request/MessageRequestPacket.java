package com.baayso.springboot.netty.protocol.request;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

}
