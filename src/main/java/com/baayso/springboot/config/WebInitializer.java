package com.baayso.springboot.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.IntrospectorCleanupListener;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * Web基本配置。
 * 
 * @author ChenFangjie (2015年9月19日 下午10:18:17)
 * 
 * @since 1.0.0
 */
@Configuration
public class WebInitializer implements ServletContextInitializer {

    // <!-- Web容器加载顺序：ServletContext -> context-param -> listener -> filter -> servlet -->

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // 防止发生java.beans.Introspector内存泄露，应将它配置在ContextLoaderListener的前面
        // 详细描述见http://blog.csdn.net/jadyer/article/details/11991457
        servletContext.addListener(IntrospectorCleanupListener.class);

        // Log4jConfigListener
        // servletContext.setInitParameter("log4jConfigLocation", "classpath:config/properties/log4j.properties");
        // servletContext.addListener(Log4jConfigListener.class);

        // 配置Druid内置的StatViewServlet用于展示Druid的统计信息
        StatViewServlet druidStatView = new StatViewServlet();
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("druidStatView", druidStatView);
        // dynamic.setLoadOnStartup(2);
        dynamic.addMapping("/druid/*");
    }

}
