package com.baayso.springboot.config;

import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * 配置Druid和Spring关联监控配置。
 *
 * @author ChenFangjie (2016/4/4 18:01)
 * @since 1.0.0
 */
@Configuration
public class DruidStatInterceptorConfig {

    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(new DruidStatInterceptor());
        advisor.setPatterns("com.baayso..service.*", "com.baayso..dao.*");

        return advisor;
    }

}
