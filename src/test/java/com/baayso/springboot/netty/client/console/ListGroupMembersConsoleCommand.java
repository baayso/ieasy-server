package com.baayso.springboot.netty.client.console;

import java.util.Scanner;

import com.baayso.springboot.netty.protocol.request.ListGroupMembersRequestPacket;

import io.netty.channel.Channel;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId，获取群成员列表：");
        Long groupId = Long.valueOf(scanner.next());

        ListGroupMembersRequestPacket request = new ListGroupMembersRequestPacket();
        request.setGroupId(groupId);

        channel.writeAndFlush(request);
    }

}
