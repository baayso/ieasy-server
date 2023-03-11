package com.baayso.springboot.netty.serialize.impl;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.baayso.commons.utils.DateTimeUtils;
import com.baayso.springboot.netty.serialize.Serializer;
import com.baayso.springboot.netty.serialize.SerializerAlgorithm;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONSerializer implements Serializer {

    private final ObjectMapper objectMapper;

    public JSONSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // default-property-inclusion
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化为JSON时将 Long 转为 String（全局配置）
        // mapper.registerModule(new SimpleModule().addSerializer(Long.class, ToStringSerializer.instance));

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

        mapper.registerModule(timeModule);
        // mapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        this.objectMapper = mapper;
    }

    public JSONSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        try {
            return this.objectMapper.writeValueAsBytes(object);
        }
        catch (Exception e) {
            log.warn("write to json bytes error: " + object, e);
        }

        return null;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            return this.objectMapper.readValue(bytes, clazz);
        }
        catch (Exception e) {
            String str = StringUtils.toEncodedString(bytes, StandardCharsets.UTF_8);
            log.warn("parse json bytes error: " + str, e);
        }

        return null;
    }

}
