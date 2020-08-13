package com.baayso.springboot.tenant.dao;

import com.baayso.springboot.common.mybatis.mapper.BaseMapper;
import com.baayso.springboot.tenant.domain.TenantDO;
import com.baomidou.mybatisplus.annotation.SqlParser;

/**
 * 数据访问：租户。
 *
 * @author ChenFangjie (2020/08/12 18:29:51)
 * @since 4.0.0
 */
public interface TenantDAO extends BaseMapper<TenantDO> {

    /**
     * 创建租户的数据库。
     *
     * @param name 数据库名称
     */
    @SqlParser(filter = true)
    int createDatabase(String name);

    /**
     * 在指定的数据库中创建数据表。
     *
     * @param databaseName 数据库名称
     */
    @SqlParser(filter = true)
    void createTable(String databaseName);

}
