package com.baayso.springboot.common.service;

import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.common.mybatis.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public PageVO<T> page(int pageNum, int pageSize) {
        IPage<T> page = this.page(
                new Page<>(pageNum, pageSize),
                Wrappers.emptyWrapper());

        return PageVO.creator(page);
    }

    public PageVO<T> page(int pageNum, int pageSize, Wrapper<T> queryWrapper) {
        IPage<T> page = this.page(
                new Page<>(pageNum, pageSize),
                queryWrapper);

        return PageVO.creator(page);
    }

    public PageVO<T> page2(IPage<T> page) {
        return PageVO.creator(super.page(page, Wrappers.emptyWrapper()));
    }

    public PageVO<T> page2(IPage<T> page, Wrapper<T> queryWrapper) {
        return PageVO.creator(super.page(page, queryWrapper));
    }

}
