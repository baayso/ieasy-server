package com.baayso.springboot.netty.client.console;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.baayso.springboot.netty.protocol.request.CreateGroupRequestPacket;

import io.netty.channel.Channel;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SEPARATOR = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入userId列表，userId之间英文逗号隔开：");
        String userIds = scanner.next();

        String[] userIdArr = userIds.split(USER_ID_SEPARATOR);
        List<Long> userIdList = Arrays.stream(userIdArr).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

        CreateGroupRequestPacket request = new CreateGroupRequestPacket();
        request.setUserIds(userIdList);

        channel.writeAndFlush(request);
    }

}
