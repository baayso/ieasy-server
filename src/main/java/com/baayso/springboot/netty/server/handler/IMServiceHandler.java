package com.baayso.springboot.netty.server.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baayso.commons.sequence.Sequence;
import com.baayso.springboot.netty.protocol.Packet;
import com.baayso.springboot.netty.protocol.command.Command;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Component
@ChannelHandler.Sharable
public class IMServiceHandler extends SimpleChannelInboundHandler<Packet> {

    private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMServiceHandler(Sequence sequence) {
        this.handlerMap = new HashMap<>();
        this.handlerMap.put(Command.MESSAGE_REQUEST, new MessageRequestHandler());
        this.handlerMap.put(Command.CREATE_GROUP_REQUEST, new CreateGroupRequestHandler(sequence));
        this.handlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, new ListGroupMembersRequestHandler());
        this.handlerMap.put(Command.JOIN_GROUP_REQUEST, new JoinGroupRequestHandler());
        this.handlerMap.put(Command.QUIT_GROUP_REQUEST, new QuitGroupRequestHandler());
        this.handlerMap.put(Command.GROUP_MESSAGE_REQUEST, new GroupMessageRequestHandler());
        this.handlerMap.put(Command.LOGOUT_REQUEST, new LogoutRequestHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        byte command = msg.getCommand();
        SimpleChannelInboundHandler<? extends Packet> handler = this.handlerMap.get(command);
        handler.channelRead(ctx, msg);
    }

}
