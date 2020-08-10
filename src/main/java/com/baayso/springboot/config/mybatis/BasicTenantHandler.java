package com.baayso.springboot.config.mybatis;

import javax.servlet.http.HttpServletRequest;

import com.baayso.springboot.common.controller.CommonController;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.ValueListExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

/**
 * 租户处理器(TenantId 行级)。
 *
 * @author ChenFangjie (2020/8/9 11:11)
 * @since 4.0.0
 */
public class BasicTenantHandler implements TenantHandler {

    @Override
    public Expression getTenantId(boolean select) {

        HttpServletRequest request = CommonController.getRequest();
        String tenantCode = request.getHeader("tenantCode");

        Expression expression;

        // select 参数为 true 表示 select 下的 where 条件，允许多参
        if (select) {
            ExpressionList list = new ExpressionList(new StringValue(tenantCode));

            ValueListExpression valueListExpression = new ValueListExpression();
            valueListExpression.setExpressionList(list);

            expression = valueListExpression;
        }
        else { // select 参数为 false 表示 insert/update/delete 下的条件，只支持单参
            expression = new StringValue(tenantCode);
        }

        return expression;
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_code";
    }

    @Override
    public boolean doTableFilter(String tableName) {
        // 这里可以判断是否过滤表

        return false;
    }

}
