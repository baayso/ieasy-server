package com.baayso.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务启动时执行。
 *
 * @author ChenFangjie (2018/3/23 23:05)
 * @since 2.0.0
 */
@Component
@Order(value = 1)
public class TestCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>> 服务启动时执行，执行加载数据等操作 <<<<<<<<<<<<<");
    }

}
