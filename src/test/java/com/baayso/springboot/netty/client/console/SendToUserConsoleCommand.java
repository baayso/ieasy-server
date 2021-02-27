package com.baayso.springboot.netty.client.console;

import java.util.Scanner;

import com.baayso.springboot.netty.protocol.request.MessageRequestPacket;

import io.netty.channel.Channel;

public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给指定的用户：");

        Long toUserId = Long.valueOf(scanner.next());
        String message = scanner.next();

        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }

}
