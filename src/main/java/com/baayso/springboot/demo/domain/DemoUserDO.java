package com.baayso.springboot.demo.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 数据对象：测试实体。
 *
 * @author ChenFangjie (2016/4/1 16:19)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("demo_user")
public class DemoUserDO implements Serializable {

    private static final long serialVersionUID = -8962566780883631270L;

    private Long        id;
    private Long        tenantId; // 租户ID
    private String      name;
    private Integer     age;
    private OrderStatus status;
    private String      intro;

    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private Date datetime;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("tenantId", tenantId)
                .append("name", name)
                .append("age", age)
                .append("status", status)
                .append("intro", intro)
                .append("deleted", deleted)
                .append("datetime", datetime)
                .toString();
    }

}
