package com.baayso.springboot.netty.protocol.request;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMessageRequestPacket extends Packet {

    private Long   toGroupId;
    private String message;

    public GroupMessageRequestPacket() {
    }

    public GroupMessageRequestPacket(Long toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }

}
