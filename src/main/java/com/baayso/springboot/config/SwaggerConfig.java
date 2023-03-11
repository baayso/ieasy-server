package com.baayso.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger Config。
 *
 * @author ChenFangjie (2023/3/4 16:54)
 * @since 4.0.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("iEasy Server Spring Boot 脚手架项目服务端API")
                .description("iEasy Server Spring Boot 脚手架项目服务端API")
                .termsOfService("")
                // .contact(new Contact("name", "url", "email"))
                // .license(new License().name("").url(""))
                .version("1.0.0");

        return new OpenAPI().info(info);
    }

}
