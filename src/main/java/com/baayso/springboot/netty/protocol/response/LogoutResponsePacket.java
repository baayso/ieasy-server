package com.baayso.springboot.netty.protocol.response;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponsePacket extends Packet {

    private boolean success;
    private String  reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

}
