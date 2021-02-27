package com.baayso.springboot.netty.client.console;

import java.util.Scanner;

import com.baayso.springboot.netty.protocol.request.LogoutRequestPacket;

import io.netty.channel.Channel;

public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket request = new LogoutRequestPacket();

        channel.writeAndFlush(request);
    }

}
