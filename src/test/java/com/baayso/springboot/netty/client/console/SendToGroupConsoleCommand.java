package com.baayso.springboot.netty.client.console;

import java.util.Scanner;

import com.baayso.springboot.netty.protocol.request.GroupMessageRequestPacket;

import io.netty.channel.Channel;

public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给指定的群组（groupId 消息内容）：");

        Long toGroupId = Long.valueOf(scanner.next());
        String message = scanner.next();

        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }

}
