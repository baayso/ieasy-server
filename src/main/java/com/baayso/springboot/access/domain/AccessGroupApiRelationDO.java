package com.baayso.springboot.access.domain;

import javax.persistence.Table;

import com.baayso.springboot.common.domain.BaseDO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据对象：接入方和API关联对象。
 *
 * @author ChenFangjie (2017/4/17 10:09)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "system_access_group_api_relation")
public class AccessGroupApiRelationDO extends BaseDO {

    private static final long serialVersionUID = -1553896700967416906L;

    private Long groupId;        // 角色ID
    private Long apiId;          // API_ID

}
