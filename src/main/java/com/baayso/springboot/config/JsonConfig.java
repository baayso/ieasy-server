package com.baayso.springboot.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.baayso.commons.json.Jackson2JsonSerializer;
import com.baayso.commons.json.JsonSerializer;
import com.baayso.commons.utils.DateTimeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * JSON序列化和反序列化配置。
 *
 * @author ChenFangjie (2023/3/6 16:22)
 * @see org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
 * @since 4.0.0
 */
@Configuration
public class JsonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(ApplicationContext applicationContext,
                                                                   List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        // default-property-inclusion
        // builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        // builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        // builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);

        builder.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        builder.featuresToEnable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // builder.timeZone("Asia/Shanghai");
        builder.timeZone("GMT+8:00");

        // 针对SimpleDateFormat线程不安全，建议使用自定义的日期工具类。不设置的情况下，默认返回时间戳
        builder.dateFormat(DateTimeUtils.SIMPLE_DATE_FORMAT.get());

        builder.applicationContext(applicationContext);

        for (Jackson2ObjectMapperBuilderCustomizer customizer : customizers) {
            customizer.customize(builder);
        }

        LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(DateTimeUtils.DATE_TIME_FORMATTER_SEPARATOR);
        LocalDateSerializer dateSerializer = new LocalDateSerializer(DateTimeUtils.DATE_FORMATTER_SEPARATOR);
        LocalTimeSerializer timeSerializer = new LocalTimeSerializer(DateTimeUtils.TIME_FORMATTER_SEPARATOR);
        LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeUtils.DATE_TIME_FORMATTER_SEPARATOR);
        LocalDateDeserializer dateDeserializer = new LocalDateDeserializer(DateTimeUtils.DATE_FORMATTER_SEPARATOR);
        LocalTimeDeserializer timeDeserializer = new LocalTimeDeserializer(DateTimeUtils.TIME_FORMATTER_SEPARATOR);

        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);
        timeModule.addSerializer(LocalDate.class, dateSerializer);
        timeModule.addSerializer(LocalTime.class, timeSerializer);
        timeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        timeModule.addDeserializer(LocalDate.class, dateDeserializer);
        timeModule.addDeserializer(LocalTime.class, timeDeserializer);

        // 序列化为JSON时将 Long 转为 String（全局配置）
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);

        List<Module> modules = new ArrayList<>();
        modules.add(timeModule);
        modules.add(simpleModule);

        Collection<Module> moduleBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Module.class).values();
        modules.addAll(moduleBeans);

        builder.modulesToInstall(modules.toArray(new Module[0]));

        return builder;
    }

    @Bean
    @Primary
    // Jackson的ObjectMapper是线程安全的，但是在SpringBoot源码中使用的是非单例模式
    // @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConditionalOnBean(Jackson2ObjectMapperBuilder.class)
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(false).build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Bean("myJsonSerializer")
    public JsonSerializer jsonSerializer(ObjectMapper objectMapper) {
        return new Jackson2JsonSerializer(objectMapper);
    }

}
