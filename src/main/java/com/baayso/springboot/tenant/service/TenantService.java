package com.baayso.springboot.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baayso.springboot.common.service.AbstractBaseService;
import com.baayso.springboot.tenant.dao.TenantDAO;
import com.baayso.springboot.tenant.domain.TenantDO;

/**
 * 业务逻辑：租户。
 *
 * @author ChenFangjie (2020/08/12 18:29:51)
 * @since 4.0.0
 */
@Service
public class TenantService extends AbstractBaseService<TenantDAO, TenantDO> {

    private final TenantDAO tenantDAO;

    @Autowired
    public TenantService(TenantDAO tenantDAO) {
        this.tenantDAO = tenantDAO;
    }

    /**
     * 新增租户。
     *
     * @param tenant 数据对象
     *
     * @return 是否成功
     */
    @Transactional
    public boolean create(TenantDO tenant) {
        boolean successful = super.save(tenant);

        if (successful) {
            successful = this.createDatabase(tenant.getCode());
        }

        if (successful) {
            this.tenantDAO.createTable(tenant.getCode());
        }

        return successful;
    }

    /**
     * 创建租户的数据库。
     *
     * @param name 数据库名称
     */
    private boolean createDatabase(String name) {
        return this.tenantDAO.createDatabase(name) > 0;
    }

}
