package com.baayso.springboot.netty.client.console;

import java.util.Scanner;

import com.baayso.springboot.netty.protocol.request.QuitGroupRequestPacket;

import io.netty.channel.Channel;

public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId，退出群聊：");
        Long groupId = Long.valueOf(scanner.next());

        QuitGroupRequestPacket request = new QuitGroupRequestPacket();
        request.setGroupId(groupId);

        channel.writeAndFlush(request);
    }

}
