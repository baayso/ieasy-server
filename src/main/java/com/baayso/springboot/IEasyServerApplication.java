package com.baayso.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import net.hasor.spring.boot.WorkAt;

/**
 * 程序启动类。
 *
 * @author ChenFangjie (2016/3/31 14:32)
 * @since 1.0.0
 */
// 在Spring 中启用 Hasor
@EnableHasor()
// 将 hasor-web 配置到 Spring 环境中，Dataway 的 UI 是通过 hasor-web 提供服务
@EnableHasorWeb(path = "/app/*", at = WorkAt.Interceptor)
@EnableAsync
@SpringBootApplication
public class IEasyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IEasyServerApplication.class, args);
    }

}
