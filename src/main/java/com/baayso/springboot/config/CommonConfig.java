package com.baayso.springboot.config;

import java.util.List;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.baayso.commons.sequence.Sequence;
import com.baayso.commons.spring.SpringUtils;
import com.baayso.commons.utils.JsonUtils;
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
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());

        List<HttpMessageConverter<?>> messageConverterList = restTemplate.getMessageConverters();

        for (HttpMessageConverter<?> messageConverter : messageConverterList) {
            if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) messageConverter;
                converter.setObjectMapper(JsonUtils.INSTANCE.getMapper());
            }
        }

        return restTemplate;
    }

    @Bean
    public static SpringUtils springUtils() {
        return new SpringUtils();
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
