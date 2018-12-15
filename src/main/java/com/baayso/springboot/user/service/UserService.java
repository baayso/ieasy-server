package com.baayso.springboot.user.service;

import org.springframework.stereotype.Service;

import com.baayso.springboot.common.service.AbstractBaseService;
import com.baayso.springboot.user.dao.UserDAO;
import com.baayso.springboot.user.domain.UserDO;

/**
 * 服务：用户。
 *
 * @author ChenFangjie (2018/12/14 22:08)
 * @since 3.0.0
 */
@Service
public class UserService extends AbstractBaseService<UserDAO, UserDO> {

}
