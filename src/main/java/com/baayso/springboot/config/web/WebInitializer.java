package com.baayso.springboot.config.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.IntrospectorCleanupListener;

/**
 * Web基本配置。
 *
 * @author ChenFangjie (2015年9月19日 下午10:18:17)
 * @since 1.0.0
 */
@Configuration
public class WebInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // <!-- Web容器加载顺序：ServletContext -> context-param -> listener -> filter -> servlet -->

        // 防止发生java.beans.Introspector内存泄露，应将它配置在ContextLoaderListener的前面
        // 详细描述见http://blog.csdn.net/jadyer/article/details/11991457
        servletContext.addListener(IntrospectorCleanupListener.class);
    }

}
