package com.baayso.springboot.netty.protocol.response;

import java.util.List;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupResponsePacket extends Packet {

    private boolean      success;
    private Long         groupId;
    private List<String> usernames;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }

}
