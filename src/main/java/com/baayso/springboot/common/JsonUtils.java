package com.baayso.springboot.common;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import org.springside.modules.mapper.JsonMapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类。
 *
 * @author ChenFangjie (2015/12/9 20:22)
 * @since 1.0.0
 */
public final class JsonUtils {

    // 让工具类彻底不可以实例化
    private JsonUtils() {
        throw new Error("工具类不可以实例化！");
    }

    private static final JsonMapper mapper;

    static {
        mapper = new JsonMapper();
        getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。
     * <p>
     * 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
     *
     * @since 1.0.0
     */
    public static String toJson(Object object) {
        return mapper.toJson(object);
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     * <p>
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
     *
     * @since 1.0.0
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return mapper.fromJson(jsonString, clazz);
    }

    /**
     * 反序列化复杂Collection如List<Bean>, contructCollectionType()或contructMapType()构造类型, 然后调用本函数.
     *
     * @since 1.0.0
     */
    public static <T> T fromJson(String jsonString, JavaType javaType) {
        return mapper.fromJson(jsonString, javaType);
    }

    /**
     * 构造Collection类型.
     *
     * @since 1.0.0
     */
    public static JavaType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return mapper.contructCollectionType(collectionClass, elementClass);
    }

    /**
     * 构造Map类型.
     *
     * @since 1.0.0
     */
    public static JavaType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.contructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
     *
     * @since 1.0.0
     */
    public static void update(String jsonString, Object object) {
        mapper.update(jsonString, object);
    }

    /**
     * 輸出JSONP格式數據.
     *
     * @since 1.0.0
     */
    public static String toJsonP(String functionName, Object object) {
        return mapper.toJsonP(functionName, object);
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum, 為False時時使用Enum的name()函數來讀寫Enum, 默認為False. 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     *
     * @since 1.0.0
     */
    public static void enableEnumUseToString() {
        mapper.enableEnumUseToString();
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     *
     * @since 1.0.0
     */
    public static ObjectMapper getObjectMapper() {
        return mapper.getMapper();
    }

}
