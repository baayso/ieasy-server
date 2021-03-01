package com.baayso.springboot.netty.client.console;

import java.util.Scanner;

import com.baayso.springboot.netty.protocol.request.JoinGroupRequestPacket;

import io.netty.channel.Channel;

public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId，加入群聊：");
        Long groupId = Long.valueOf(scanner.next());

        JoinGroupRequestPacket request = new JoinGroupRequestPacket();
        request.setGroupId(groupId);

        channel.writeAndFlush(request);
    }

}
