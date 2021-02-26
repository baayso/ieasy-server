package com.baayso.springboot.netty.protocol.response;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

}
