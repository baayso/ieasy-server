package com.baayso.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 测试实体。
 *
 * @author ChenFangjie (2016/4/1 16:19)
 * @since 1.0.0
 */
@Table(name = "t_test_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = -8962566780883631270L;

    private Long   id;
    private String name;
    @Transient
    private Date   datetime;

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}
