package com.baayso.springboot.common.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springdoc.webmvc.api.MultipleOpenApiResource;
import org.springdoc.webmvc.ui.SwaggerConfigResource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.baayso.commons.tool.BasicResponseStatus;
import com.baayso.commons.utils.JsonUtils;
import com.baayso.springboot.common.domain.ResultVO;

/**
 * 全局响应数据处理器。
 *
 * @author ChenFangjie (2020/8/8 8:8)
 * @since 4.0.0
 */
@RestControllerAdvice
public class ResponseRestControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {

        Class<?> clazz = null;

        Type genericParameterType = returnType.getGenericParameterType();
        Class<?> declaringClass = returnType.getDeclaringClass();

        if (genericParameterType instanceof Class<?>) {
            clazz = (Class<?>) genericParameterType;
        }
        else if (genericParameterType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType.getGenericParameterType();
            clazz = (Class<?>) type.getRawType();
        }

        boolean invoke = ResultVO.class.equals(clazz) ||
                ResponseEntity.class.equals(clazz) ||
                MultipleOpenApiResource.class.isAssignableFrom(declaringClass) ||
                SwaggerConfigResource.class.isAssignableFrom(declaringClass);

        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        return !invoke;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 将原本的数据包装在ResultVO里
        ResultVO<Object> result = new ResultVO<>();
        result.setSuccess(true);
        result.setCode(BasicResponseStatus.OK.value());
        result.setMessage(BasicResponseStatus.OK.getReason());
        result.setData(body);

        // String类型需主动转为JSON字符串，否则会报
        // com.baayso.springboot.common.domain.ResultVO cannot be cast to java.lang.String

        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            // 将数据包装在ResultVO里后，再转换为json字符串响应给客户端
            return JsonUtils.INSTANCE.toJson(result);
        }

        return result;
    }

}
