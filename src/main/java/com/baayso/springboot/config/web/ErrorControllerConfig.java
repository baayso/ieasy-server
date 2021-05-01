package com.baayso.springboot.config.web;

import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.baayso.springboot.common.controller.CustomBasicErrorController;

/**
 * Exception Controller Config.
 *
 * @author ChenFangjie (2020/8/8 8:30)
 * @since 4.0.0
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(WebProperties.class)
public class ErrorControllerConfig {

    private final ServerProperties        serverProperties;
    private final List<ErrorViewResolver> errorViewResolvers;

    public ErrorControllerConfig(ServerProperties serverProperties,
                                 ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
    }

    @Bean
    public ErrorController errorController(ErrorAttributes errorAttributes) {
        return new CustomBasicErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
    }

}
