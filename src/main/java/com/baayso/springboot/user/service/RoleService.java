package com.baayso.springboot.user.service;

import org.springframework.stereotype.Service;

import com.baayso.springboot.common.service.AbstractBaseService;
import com.baayso.springboot.user.dao.RoleDAO;
import com.baayso.springboot.user.domain.RoleDO;

/**
 * 服务：角色。
 *
 * @author ChenFangjie (2018/12/14 22:09)
 * @since 3.0.0
 */
@Service
public class RoleService extends AbstractBaseService<RoleDAO, RoleDO> {

}
