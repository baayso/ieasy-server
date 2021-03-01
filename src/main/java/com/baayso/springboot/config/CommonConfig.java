package com.baayso.springboot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.baayso.commons.security.AESCoder;
import com.baayso.commons.security.password.BCryptPasswordEncoder;
import com.baayso.commons.security.password.PasswordEncoder;
import com.baayso.commons.sequence.Sequence;
import com.baayso.commons.spring.SpringUtils;
import com.baayso.commons.utils.Validator;
import com.baayso.springboot.common.message.ErrorFactory;

/**
 * 公共配置。
 *
 * @author ChenFangjie (2016/4/11 15:52)
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(I18nProperties.class)
public class CommonConfig {

    // application.yml中的配置会注入到I18nProperties中
    public CommonConfig(I18nProperties properties) {
        ErrorFactory.initMessageSource(properties.getIsvModules());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    public static SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Bean
    public AESCoder aesCoder() {
        return new AESCoder("G7jX/RpqbqzbvUoxJ2fEaVdhk8e/axGXbhEXli2dR0TI=");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator validator() {
        return new Validator();
    }

    @Bean
    public Sequence sequence() {
        return new Sequence(0);
    }

}
