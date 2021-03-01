package com.baayso.springboot.netty.protocol.request;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

}
