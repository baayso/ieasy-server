package com.baayso.springboot.netty.protocol.request;

import java.util.List;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequestPacket extends Packet {

    private List<Long> userIds;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

}

