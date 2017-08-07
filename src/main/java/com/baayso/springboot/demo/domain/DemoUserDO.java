package com.baayso.springboot.demo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.baayso.springboot.demo.domain.status.OrderStatus;

/**
 * 数据对象：测试实体。
 *
 * @author ChenFangjie (2016/4/1 16:19)
 * @since 1.0.0
 */
@Table(name = "demo_user")
public class DemoUserDO implements Serializable {

    private static final long serialVersionUID = -8962566780883631270L;

    @Id
    @GeneratedValue(generator = "JDBC")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    private String      name;
    private OrderStatus status;
    private String      intro;
    @Transient
    private Date        datetime;

    @Override
    public String toString() {
        return "DemoUserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", intro='" + intro + '\'' +
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
