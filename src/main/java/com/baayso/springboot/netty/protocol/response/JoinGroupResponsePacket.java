package com.baayso.springboot.netty.protocol.response;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinGroupResponsePacket extends Packet {

    private Long    groupId;
    private boolean success;
    private String  reason;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }

}
