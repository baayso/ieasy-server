package com.baayso.springboot.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baayso.commons.interceptor.PerformanceInterceptor;
import com.baayso.springboot.common.interceptor.TenantInterceptor;

/**
 * Spring MVC 配置。
 *
 * @author ChenFangjie (2016/4/13 13:56)
 * @since 1.0.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public TenantInterceptor tenantInterceptor() {
        return new TenantInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.performanceInterceptor())
                .addPathPatterns("/demo/**")
                .addPathPatterns("/api/**")
                .addPathPatterns("/app/api/**");

        registry.addInterceptor(this.tenantInterceptor())
                .addPathPatterns("/demo/**")
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/tenant/**");

        //registry.addInterceptor(new AccessTokenVerificationInterceptor())
        //        .addPathPatterns("/api/**")
        //        .excludePathPatterns("/api/token")
        //        .excludePathPatterns("/api/order/alipay/web/notify");

        //registry.addInterceptor(new DataDigestInterceptor())
        //        .addPathPatterns("/api/**");

        //registry.addInterceptor(new AccessApiAuthenticationInterceptor())
        //        .addPathPatterns("/api/**")
        //        .excludePathPatterns("/api/token");
    }

    // CORS全局配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");

        // registry.addMapping("/**") // 允许跨域访问的路径
        //         .allowedOrigins("*") // 允许跨域访问的源
        //         .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许请求方法
        //         .maxAge(168000L) // 预检间隔时间
        //         .allowedHeaders("*") // 允许头部设置
        //         .allowCredentials(true); // 是否发送cookie
    }

}
