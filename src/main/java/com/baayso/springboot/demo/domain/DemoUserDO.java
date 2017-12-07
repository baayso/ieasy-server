package com.baayso.springboot.demo.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 * 数据对象：测试实体。
 *
 * @author ChenFangjie (2016/4/1 16:19)
 * @since 1.0.0
 */
@TableName("demo_user")
public class DemoUserDO implements Serializable {

    private static final long serialVersionUID = -8962566780883631270L;

    private Long        id;
    private Long        tenantId; // 租户ID
    private String      name;
    private Integer     age;
    private OrderStatus status;
    private String      intro;

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
                .append("datetime", datetime)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}
