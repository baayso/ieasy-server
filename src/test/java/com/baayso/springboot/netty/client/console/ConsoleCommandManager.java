package com.baayso.springboot.netty.client.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.Channel;

public class ConsoleCommandManager implements ConsoleCommand {

    private final Map<String, ConsoleCommand> consoleCommandMap = new HashMap<>();

    public ConsoleCommandManager() {
        this.consoleCommandMap.put("logout", new LogoutConsoleCommand());
        this.consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        this.consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        this.consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
        this.consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        this.consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 获取第一个指令
        String command = scanner.next();

        if (!SessionUtils.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = this.consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        }
        else {
            System.err.println("无法识别[" + command + "]指令，请重新输入！");
        }
    }

}
