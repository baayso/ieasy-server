package com.baayso.springboot.demo.dao;

import java.util.List;

import com.baayso.springboot.common.mybatis.mapper.BaseMapper;
import com.baayso.springboot.demo.domain.DemoUserDO;

/**
 * 测试数据访问。
 *
 * @author ChenFangjie (2016/4/1 16:24)
 * @since 1.0.0
 */
public interface DemoUserDAO extends BaseMapper<DemoUserDO> {

    List<DemoUserDO> listUnion();

    List<DemoUserDO> listInnerJoin();

    List<DemoUserDO> listLeftJoin();

    List<DemoUserDO> listRightJoin();

    List<DemoUserDO> listSubQuery();

    int inserts();

    int insertIntoSelect();

}
