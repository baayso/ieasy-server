package com.baayso.springboot.netty.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Netty Server 启动器。
 *
 * @author ChenFangjie (2021/2/23 18:57)
 * @since 4.0.0
 */
@Slf4j
@Component
@Order(value = 2)
public class NettyServerStarter implements CommandLineRunner {

    private final NettyServer nettyServer;

    public NettyServerStarter(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    public void run(String... args) {
        this.nettyServer.start();
    }

}
