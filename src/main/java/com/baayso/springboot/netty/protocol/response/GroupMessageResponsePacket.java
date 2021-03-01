package com.baayso.springboot.netty.protocol.response;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;
import com.baayso.springboot.netty.session.Session;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMessageResponsePacket extends Packet {

    private Long    fromGroupId;
    private Session fromUser;
    private String  message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }

}
