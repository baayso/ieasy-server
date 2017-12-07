package com.baayso.springboot.access.domain;

import com.baayso.springboot.common.domain.BaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据对象：接入方和接入组关联对象。
 *
 * @author ChenFangjie (2017/4/17 10:06)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("access_group_relation")
public class AccessGroupRelationDO extends BaseDO {

    private static final long serialVersionUID = -7492276935585795453L;

    private Long accessId;       // 用户ID
    private Long groupId;        // 角色ID

}
