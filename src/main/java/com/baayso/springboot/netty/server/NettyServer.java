package com.baayso.springboot.netty.server;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.baayso.springboot.common.utils.ThreadPool;
import com.baayso.springboot.netty.server.handler.ServerChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器启动类。
 *
 * @author ChenFangjie (2021/2/23 18:27)
 * @since 4.0.0
 */
@Slf4j
@Component
public class NettyServer {

    private final NioEventLoopGroup boosGroup   = new NioEventLoopGroup(1);
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup(ThreadPool.AVAILABLE_PROCESSORS);

    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    private final ServerChannelInitializer serverChannelInitializer;

    public NettyServer(ServerChannelInitializer serverChannelInitializer) {
        this.serverChannelInitializer = serverChannelInitializer;
    }

    /**
     * 关闭服务。
     */
    @PreDestroy
    public void close() {
        log.info(">>>>>>>>>>>>>>> 关闭Netty服务 <<<<<<<<<<<<<");
        // 优雅退出
        this.boosGroup.shutdownGracefully();
        this.workerGroup.shutdownGracefully();
    }

    /**
     * 启动服务。
     */
    public void start() {
        log.info(">>>>>>>>>>>>>>> 启动Netty服务 <<<<<<<<<<<<<");
        this.serverBootstrap
                .group(this.boosGroup, this.workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(this.serverChannelInitializer);

        this.bind(this.serverBootstrap, 8000);
    }

    private void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("Netty服务端口[{}]绑定成功!", port);
            }
            else {
                log.info("Netty服务端口[{}]绑定失败!", port);
            }
        });
    }

}
