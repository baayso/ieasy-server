package com.baayso.springboot.netty.serialize.impl;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import com.baayso.commons.utils.JsonUtils;
import com.baayso.springboot.netty.serialize.Serializer;
import com.baayso.springboot.netty.serialize.SerializerAlgorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        try {
            return JsonUtils.INSTANCE.getMapper().writeValueAsBytes(object);
        }
        catch (Exception e) {
            log.warn("write to json bytes error: " + object, e);
        }

        return null;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            return JsonUtils.INSTANCE.getMapper().readValue(bytes, clazz);
        }
        catch (Exception e) {
            String str = StringUtils.toEncodedString(bytes, StandardCharsets.UTF_8);
            log.warn("parse json bytes error: " + str, e);
        }

        return null;
    }

}
