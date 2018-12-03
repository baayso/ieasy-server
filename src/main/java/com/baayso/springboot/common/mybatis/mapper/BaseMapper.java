package com.baayso.springboot.common.mybatis.mapper;

/**
 * 自定义通用Mapper，继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能。
 * <p>
 * 基于：Mybatis-Plus 3.x （https://github.com/baomidou/mybatis-plus）
 *
 * @author ChenFangjie (2018/11/27 16:03)
 * @since 3.0.0
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

}
