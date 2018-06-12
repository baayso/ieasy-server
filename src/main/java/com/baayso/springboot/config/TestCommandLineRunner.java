package com.baayso.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务启动时执行。
 *
 * @author ChenFangjie (2018/3/23 23:05)
 * @since 2.0.0
 */
@Slf4j
@Component
@Order(value = 1)
public class TestCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>>>> 服务启动时执行，执行加载数据等操作 <<<<<<<<<<<<<");
    }

}
