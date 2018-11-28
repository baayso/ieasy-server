package com.baayso.springboot.common.service;

import com.baayso.springboot.common.mybatis.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 通用业务逻辑。
 *
 * @author ChenFangjie (2018/3/27 21:23)
 * @since 2.0.0
 */
public abstract class AbstractBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
