package com.baayso.springboot.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baayso.commons.interceptor.PerformanceInterceptor;

/**
 * Spring MVC 配置。
 *
 * @author ChenFangjie (2016/4/13 13:56)
 * @since 1.0.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor()).addPathPatterns("/demo/api/**");

        //registry.addInterceptor(new AccessTokenVerificationInterceptor()) //
        //        .addPathPatterns("/api/**") //
        //        .excludePathPatterns("/api/token") //
        //        .excludePathPatterns("/api/order/alipay/web/notify");

        //registry.addInterceptor(new DataDigestInterceptor()).addPathPatterns("/api/**");

        //registry.addInterceptor(new AccessApiAuthenticationInterceptor()) //
        //        .addPathPatterns("/api/**") //
        //        .excludePathPatterns("/api/token");
    }

    // CORS全局配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
