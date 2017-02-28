package com.baayso.springboot.common.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一定义主键（id）的Entity基类。
 *
 * @author ChenFangjie (2016/4/11 14:53)
 * @since 1.0.0
 */
@Getter
@Setter
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = 6836860712036654696L;

    /** 主键 */
    @Id
    @GeneratedValue(generator = "JDBC")
    protected Long id;

}
