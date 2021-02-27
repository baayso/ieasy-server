package com.baayso.springboot.netty.client.console;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.baayso.springboot.netty.protocol.request.LoginRequestPacket;

import io.netty.channel.Channel;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        LoginRequestPacket request = new LoginRequestPacket();
        request.setUsername(username);
        request.setPassword(password);

        channel.writeAndFlush(request);

        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        }
        catch (InterruptedException e) {
            // ignore
        }
    }

}
