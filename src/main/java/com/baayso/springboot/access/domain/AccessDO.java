package com.baayso.springboot.access.domain;

import javax.persistence.Table;

import com.baayso.springboot.common.domain.BaseBean;

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
@Table(name = "system_access")
public class AccessDO extends BaseBean {

    private static final long serialVersionUID = 3877696751248414559L;

    private String  name;            // 名称
    private String  code;            // 编码
    private String  type;            // 类型
    private String  accessKey;       // AccessKey
    private String  accessSecret;    // AccessSecret
    private String  salt;            // 数据盐
    private Boolean isEnable;        // 是否启用

}
