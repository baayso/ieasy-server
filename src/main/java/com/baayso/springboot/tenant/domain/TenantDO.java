package com.baayso.springboot.tenant.domain;

import java.time.LocalDateTime;

import com.baayso.springboot.common.domain.BaseDO;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据对象：租户。
 *
 * @author ChenFangjie (2020/08/12 18:29:51)
 * @since 4.0.0
 */
@Getter
@Setter
@TableName("sys_tenant")
public class TenantDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /** 租户编码 */
    private String code;

    /** 租户名称 */
    private String name;

    /** 租户类型 */
    private Integer type;

    /** 备注 */
    private String remark;

    /** 是否已禁用 */
    private Boolean disabled;

    /** 是否删除 */
    @JsonIgnore
    @TableLogic
    private Boolean deleted;

    public TenantDO() {
    }

    @Builder
    public TenantDO(String code, String name, Integer type, String remark, Boolean disabled, Boolean deleted,
                    Long id, String createBy, LocalDateTime createTime, String updateBy, LocalDateTime updateTime) {

        super(id, createBy, createTime, updateBy, updateTime);

        this.code = code;
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.disabled = disabled;
        this.deleted = deleted;
    }

}
