package com.baayso.springboot.tenant.service;

import org.springframework.stereotype.Service;

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

}
