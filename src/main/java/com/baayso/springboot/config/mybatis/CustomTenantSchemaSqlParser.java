package com.baayso.springboot.config.mybatis;

import java.util.List;

import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaHandler;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.update.Update;

/**
 * 租户SQL解析器(schema 级)。
 *
 * @author ChenFangjie (2020/8/9 13:51)
 * @since 4.0.0
 */
public class CustomTenantSchemaSqlParser extends AbstractJsqlParser {

    protected TenantSchemaHandler tenantSchemaHandler;

    @Override
    public void processInsert(Insert insert) {
        final Table table = insert.getTable();

        if (this.tenantSchemaHandler.doTableFilter(table.getName())) {
            // 过滤退出执行
            return;
        }

        table.setSchemaName(this.tenantSchemaHandler.getTenantSchema());

        if (insert.getSelect() != null) {
            processPlainSelect((PlainSelect) insert.getSelect().getSelectBody());
        }
    }

    @Override
    public void processDelete(Delete delete) {
        final Table table = delete.getTable();

        if (this.tenantSchemaHandler.doTableFilter(table.getName())) {
            // 过滤退出执行
            return;
        }

        table.setSchemaName(this.tenantSchemaHandler.getTenantSchema());
    }

    @Override
    public void processUpdate(Update update) {
        final Table table = update.getTable();

        if (this.tenantSchemaHandler.doTableFilter(table.getName())) {
            // 过滤退出执行
            return;
        }

        table.setSchemaName(this.tenantSchemaHandler.getTenantSchema());
    }

    @Override
    public void processSelectBody(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        }
        else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody());
            }
        }
        else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
                operationList.getSelects().forEach(this::processSelectBody);
            }
        }
    }

    /**
     * 处理 PlainSelect
     */
    protected void processPlainSelect(PlainSelect plainSelect) {
        FromItem fromItem = plainSelect.getFromItem();

        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (!this.tenantSchemaHandler.doTableFilter(fromTable.getName())) {
                fromTable.setSchemaName(this.tenantSchemaHandler.getTenantSchema());
            }
        }
        else {
            processFromItem(fromItem);
        }

        List<Join> joins = plainSelect.getJoins();
        if (joins != null && joins.size() > 0) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    /**
     * 处理子查询等
     */
    protected void processFromItem(FromItem fromItem) {
        if (fromItem instanceof SubJoin) {
            SubJoin subJoin = (SubJoin) fromItem;

            if (subJoin.getJoinList() != null) {
                subJoin.getJoinList().forEach(this::processJoin);
            }

            if (subJoin.getLeft() != null) {
                processFromItem(subJoin.getLeft());
            }
        }
        else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;

            if (subSelect.getSelectBody() != null) {
                processSelectBody(subSelect.getSelectBody());
            }
        }
        else if (fromItem instanceof ValuesList) {
            logger.debug("Perform a sub query, if you do not give us feedback");
        }
        else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;

            if (lateralSubSelect.getSubSelect() != null) {
                SubSelect subSelect = lateralSubSelect.getSubSelect();

                if (subSelect.getSelectBody() != null) {
                    processSelectBody(subSelect.getSelectBody());
                }
            }
        }
    }

    /**
     * 处理联接语句
     */
    protected void processJoin(Join join) {
        if (join.getRightItem() instanceof Table) {
            Table fromTable = (Table) join.getRightItem();

            if (this.tenantSchemaHandler.doTableFilter(fromTable.getName())) {
                // 过滤退出执行
                return;
            }

            fromTable.setSchemaName(this.tenantSchemaHandler.getTenantSchema());
        }
    }

    // ==========================================================================

    public TenantSchemaHandler getTenantSchemaHandler() {
        return tenantSchemaHandler;
    }

    public void setTenantSchemaHandler(TenantSchemaHandler tenantSchemaHandler) {
        this.tenantSchemaHandler = tenantSchemaHandler;
    }

}
