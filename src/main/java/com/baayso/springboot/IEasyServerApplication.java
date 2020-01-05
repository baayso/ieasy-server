package com.baayso.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 程序启动类。
 *
 * @author ChenFangjie (2016/3/31 14:32)
 * @since 1.0.0
 */
@EnableAsync
@SpringBootApplication
public class IEasyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IEasyServerApplication.class, args);
    }

}
