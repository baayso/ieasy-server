package com.baayso.springboot.user.service;

import org.springframework.stereotype.Service;

import com.baayso.springboot.common.service.AbstractBaseService;
import com.baayso.springboot.user.dao.UserGroupDAO;
import com.baayso.springboot.user.domain.UserGroupDO;

/**
 * 服务：用户组。
 *
 * @author ChenFangjie (2018/12/14 22:10)
 * @since 3.0.0
 */
@Service
public class UserGroupService extends AbstractBaseService<UserGroupDAO, UserGroupDO> {

}
