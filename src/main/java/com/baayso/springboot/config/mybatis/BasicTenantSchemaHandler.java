package com.baayso.springboot.config.mybatis;

import javax.servlet.http.HttpServletRequest;

import com.baayso.springboot.common.controller.CommonController;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaHandler;

/**
 * 租户处理器(schema 级)。
 *
 * @author ChenFangjie (2020/8/9 11:13)
 * @since 4.0.0
 */
public class BasicTenantSchemaHandler implements TenantSchemaHandler {

    @Override
    public String getTenantSchema() {
        HttpServletRequest request = CommonController.getRequest();
        String tenantCode = request.getHeader("tenantCode");

        return "ieasy_tenant_1";
    }

    @Override
    public boolean doTableFilter(String tableName) {
        // 这里可以判断是否过滤表

        return false;
    }

}
