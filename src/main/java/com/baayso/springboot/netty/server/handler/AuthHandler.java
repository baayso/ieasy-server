package com.baayso.springboot.netty.server.handler;

import com.baayso.springboot.netty.utils.LoginUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtils.hasLogin(ctx.channel())) {
            ctx.channel().close();
        }
        else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (LoginUtils.hasLogin(ctx.channel())) {
            log.info("当前连接登录验证完毕，无需再次验证，AuthHandler被移除！");
        }
        else {
            log.info("无登录验证，强制关闭连接！");
        }
    }

}
