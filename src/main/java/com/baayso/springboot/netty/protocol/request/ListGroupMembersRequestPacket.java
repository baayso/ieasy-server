package com.baayso.springboot.netty.protocol.request;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListGroupMembersRequestPacket extends Packet {

    private Long groupId;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }

}
