package com.baayso.springboot.config.mybatis;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.baayso.springboot.common.controller.CommonController;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

/**
 * 行级租户SQL解析器。
 *
 * @author ChenFangjie (2020/8/9 11:11)
 * @since 4.0.0
 */
public class BasicTenantLineHandler implements TenantLineHandler {

    @Override
    public Expression getTenantId() {
        HttpServletRequest request = CommonController.getRequest();
        String tenantCode = request.getHeader("tenantCode");

        return new StringValue(tenantCode);
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_code";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        // 这里可以判断是否过滤表
        return Objects.equals(tableName, "sys_tenant");
    }

}
