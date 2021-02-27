package com.baayso.springboot.netty.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.baayso.commons.sequence.Sequence;
import com.baayso.springboot.netty.protocol.request.CreateGroupRequestPacket;
import com.baayso.springboot.netty.protocol.response.CreateGroupResponsePacket;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    private final Sequence sequence;

    public CreateGroupRequestHandler(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket request) {
        // 1. 创建一个channel分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2. 筛选出待加入群聊的用户的 channel 和 username
        List<Long> userIds = request.getUserIds();
        List<String> usernames = new ArrayList<>();
        for (Long userId : userIds) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                usernames.add(SessionUtils.getSession(channel).getUsername());
            }
        }

        // 3. 创建群聊创建结果的响应
        CreateGroupResponsePacket response = new CreateGroupResponsePacket();
        response.setSuccess(true);
        response.setGroupId(this.sequence.nextId());
        response.setUsernames(usernames);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(response);

        log.info("群创建成功，群ID为：{}，群里包含的用户有：{}", response.getGroupId(), response.getUsernames());
    }

}
