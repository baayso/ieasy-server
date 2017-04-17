package com.baayso.springboot.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baayso.springboot.common.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * 通用控制器。
 *
 * @author ChenFangjie (2016/12/12 14:43)
 * @since 1.0.0
 */
public class CommonController {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static String getAccessToken() {
        return getRequest().getParameter("accessToken");
    }

    public static String getToken() {
        return getRequest().getParameter("token");
    }

    public boolean validateJson(JsonNode schema, String json) {
        try {
            JsonNode data = JsonUtils.INSTANCE.getMapper().readTree(json);

            ProcessingReport report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);

            return report.isSuccess();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
