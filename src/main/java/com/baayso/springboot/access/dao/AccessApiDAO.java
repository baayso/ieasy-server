package com.baayso.springboot.access.dao;


import java.util.List;

import com.baayso.springboot.access.domain.AccessApiDO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * API数据访问。
 *
 * @author ChenFangjie (2017/4/17 10:22)
 * @since 1.0.0
 */
public interface AccessApiDAO extends BaseMapper<AccessApiDO> {

    /**
     * 根据接入方ID查询其拥有的API列表。
     *
     * @param accessId 接入方ID
     *
     * @return API列表
     *
     * @since 1.0.0
     */
    List<AccessApiDO> listByAccessId(Long accessId);

}
