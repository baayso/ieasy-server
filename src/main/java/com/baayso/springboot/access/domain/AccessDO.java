package com.baayso.springboot.access.domain;

import java.util.List;
import java.util.Set;

import com.baayso.springboot.common.domain.BaseDO;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据对象：接入方。
 *
 * @author ChenFangjie (2016/4/13 10:42)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("access")
public class AccessDO extends BaseDO {

    private static final long serialVersionUID = 3877696751248414559L;

    private String  name;            // 名称
    private String  code;            // 编码
    private String  type;            // 类型
    private String  accessKey;       // AccessKey
    private String  accessSecret;    // AccessSecret
    private String  salt;            // 数据盐
    private Boolean isEnable;        // 是否启用

    @TableField(exist = false)
    private List<AccessGroupDO> groups; // 接入组列表

    @TableField(exist = false)
    private Set<AccessApiDO> apis; // API列表

}
