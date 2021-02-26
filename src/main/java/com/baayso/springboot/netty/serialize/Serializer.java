package com.baayso.springboot.netty.serialize;

import com.baayso.springboot.netty.serialize.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 获取序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
