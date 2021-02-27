package com.baayso.springboot.netty.server.handler;

import java.util.Objects;

import com.baayso.commons.sequence.Sequence;
import com.baayso.springboot.netty.protocol.request.LoginRequestPacket;
import com.baayso.springboot.netty.protocol.response.LoginResponsePacket;
import com.baayso.springboot.netty.session.Session;
import com.baayso.springboot.netty.utils.SessionUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private final Sequence sequence;

    public LoginRequestHandler(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket request) {
        log.info("收到客户端登录请求……");

        LoginResponsePacket response = new LoginResponsePacket();
        response.setVersion(request.getVersion());
        response.setUsername(request.getUsername());

        if (this.valid(request)) {
            Long userId = this.sequence.nextId();
            String username = request.getUsername();

            Session session = new Session(userId, username);
            SessionUtils.bind(session, ctx.channel());

            response.setUserId(userId);
            response.setSuccess(true);

            log.info("【{}】登录成功", username);
        }
        else {
            response.setReason("用户或密码错误");
            response.setSuccess(false);

            log.info("【{}】登录失败", request.getUsername());
        }

        // 登录响应
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();

        Session session = SessionUtils.getSession(channel);
        if (session != null) {
            log.info("【{}】下线", session.getUsername());

            // 用户下线之后取消绑定
            SessionUtils.unbind(channel);
        }
    }

    private boolean valid(LoginRequestPacket request) {
        String password = request.getPassword();

        return Objects.equals(password, "123456");
    }

}
