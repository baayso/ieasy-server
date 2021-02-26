package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.protocol.request.LoginRequestPacket;
import com.baayso.springboot.netty.protocol.response.LoginResponsePacket;
import com.baayso.springboot.netty.utils.LoginUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket request) {
        log.info("收到客户端登录请求......");

        LoginResponsePacket response = new LoginResponsePacket();
        response.setVersion(request.getVersion());

        if (this.valid(request)) {
            LoginUtils.markAsLogin(ctx.channel());

            response.setSuccess(true);
            log.info("登录成功！");
        }
        else {
            response.setReason("账号密码校验失败");
            response.setSuccess(false);
            log.info("登录失败！");
        }

        // 登录响应
        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();

        super.channelReadComplete(ctx);
    }

    private boolean valid(LoginRequestPacket request) {
        return true;
    }

}
