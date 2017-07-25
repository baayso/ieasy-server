package com.baayso.springboot.config.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.IntrospectorCleanupListener;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.Getter;
import lombok.Setter;

/**
 * Web基本配置。
 *
 * @author ChenFangjie (2015年9月19日 下午10:18:17)
 * @since 1.0.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "druidStatView.login")
public class WebInitializer implements ServletContextInitializer {

    private String username;
    private String password;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // <!-- Web容器加载顺序：ServletContext -> context-param -> listener -> filter -> servlet -->

        // 防止发生java.beans.Introspector内存泄露，应将它配置在ContextLoaderListener的前面
        // 详细描述见http://blog.csdn.net/jadyer/article/details/11991457
        servletContext.addListener(IntrospectorCleanupListener.class);

        // 配置Druid内置的StatViewServlet用于展示Druid的统计信息
        ServletRegistration.Dynamic druidStatViewServlet = servletContext.addServlet("druidStatViewServlet", StatViewServlet.class);
        // 在StatViewServlet输出的html页面中，有一个功能是Reset All，执行这个操作之后，会导致所有计数器清零，重新计数。你可以通过配置参数关闭它
        druidStatViewServlet.setInitParameter("resetEnable", "false");
        druidStatViewServlet.setInitParameter("loginUsername", this.username);
        druidStatViewServlet.setInitParameter("loginPassword", this.password);
        // druidStatViewServlet.setLoadOnStartup(2);
        druidStatViewServlet.addMapping("/druid/*");

        // WebStatFilter用于采集web-jdbc关联监控的数据
        FilterRegistration.Dynamic druidWebStatFilter = servletContext.addFilter("druidWebStatFilter", WebStatFilter.class);
        druidWebStatFilter.setInitParameter("exclusions", "*.js,*.css,*.gif,*.jpg,*.png,*.ico,/druid/*");
        druidWebStatFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");

    }

}
