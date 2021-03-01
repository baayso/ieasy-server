package com.baayso.springboot.netty.protocol.response;

import java.util.List;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;
import com.baayso.springboot.netty.session.Session;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListGroupMembersResponsePacket extends Packet {

    private Long          groupId;
    private List<Session> sessions;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }

}
