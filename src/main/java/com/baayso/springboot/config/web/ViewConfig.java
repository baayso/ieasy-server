package com.baayso.springboot.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.jfinal.template.source.ClassPathSourceFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板引擎配置。
 *
 * @author ChenFangjie (2019/6/17 10:30)
 * @since 3.0.0
 */
@Slf4j
@Configuration
public class ViewConfig {

    /** Enjoy Template Engine(https://www.jfinal.com/doc/6-1) */
    @Bean
    public JFinalViewResolver jFinalViewResolver() {
        JFinalViewResolver jfr = new JFinalViewResolver();

        // setDevMode 配置放在最前面
        jfr.setDevMode(log.isDebugEnabled());

        // 使用 ClassPathSourceFactory 从 class path 与 jar 包中加载模板文件
        jfr.setSourceFactory(new ClassPathSourceFactory());

        // 在使用 ClassPathSourceFactory 时要使用 setBaseTemplatePath() 代替 jfr.setPrefix("/templates/")
        JFinalViewResolver.engine.setBaseTemplatePath("/templates/");

        jfr.setSuffix(".html");
        jfr.setContentType("text/html;charset=UTF-8");
        jfr.setOrder(0);

        jfr.addSharedFunction("common/layout.html");
        jfr.addSharedFunction("common/main.html");
        jfr.addSharedFunction("common/header.html");
        jfr.addSharedFunction("common/menu.html");
        jfr.addSharedFunction("common/footer.html");
        // jfr.addSharedFunction("/templates/common/_paginate.html");

        return jfr;
    }

}
