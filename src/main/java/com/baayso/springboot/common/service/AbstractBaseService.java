package com.baayso.springboot.common.service;

import com.baayso.springboot.common.mybatis.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 通用Service，封装了对数据库的单表操作。
 * <p>
 * 基于：Mybatis-Plus 3.x （https://github.com/baomidou/mybatis-plus）
 *
 * @author ChenFangjie (2018/11/27 16:00)
 * @since 3.0.0
 */
public abstract class AbstractBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
