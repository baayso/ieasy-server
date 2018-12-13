package com.baayso.springboot.access.domain;

import com.baayso.springboot.common.domain.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据对象：接入方与API关联对象。
 *
 * @author ChenFangjie (2017/4/17 10:09)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("access_group_and_api_ref")
public class AccessGroupAndApiRefDO extends BaseDO {

    private static final long serialVersionUID = -1553896700967416906L;

    private Long groupId;        // 角色ID
    private Long apiId;          // API_ID

}
