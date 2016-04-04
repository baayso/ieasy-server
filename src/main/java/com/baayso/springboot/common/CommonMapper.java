package com.baayso.springboot.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 自定义通用Mapper。
 *
 * @author ChenFangjie (2016/4/4 18:45)
 * @since 1.0.0
 */
public interface CommonMapper<T> extends Mapper<T>, InsertListMapper<T> {

}
