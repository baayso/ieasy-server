package com.baayso.springboot.netty.protocol.response;

import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartBeatResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }

}
