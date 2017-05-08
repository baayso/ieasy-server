package com.baayso.springboot.access.domain;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.baayso.springboot.common.domain.BaseDO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据对象：接入组。
 *
 * @author ChenFangjie (2017/2/28 16:32)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "access_group")
public class AccessGroupDO extends BaseDO {

    private static final long serialVersionUID = 6603615128410126088L;

    private String name;       // 名称
    private String code;       // 编码
    private String descr;      // 描述

    @Transient
    private List<AccessApiDO> apis; // 所拥有的API

}
