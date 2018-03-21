package com.baayso.springboot.demo2.service

import com.baayso.springboot.demo2.dao.Demo2UserDAO
import com.baayso.springboot.demo2.domain.Demo2UserDO
import com.baomidou.mybatisplus.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * 测试业务。
 *
 * @author ChenFangjie (2018/3/21 23:13)
 * @since 2.0.0
 */
@Service
class Demo2UserService : ServiceImpl<Demo2UserDAO, Demo2UserDO>()
