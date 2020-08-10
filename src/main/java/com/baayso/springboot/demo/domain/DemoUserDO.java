package com.baayso.springboot.demo.domain;

import java.time.LocalDateTime;

import com.baayso.springboot.common.domain.BaseDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 数据对象：测试实体。
 *
 * @author ChenFangjie (2016/4/1 16:19)
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("demo_user")
public class DemoUserDO extends BaseDO {

    private static final long serialVersionUID = -8962566780883631270L;

    @JsonIgnore
    private String tenantCode; // 租户编码

    private String name;

    private Integer age;

    private OrderStatus status;

    private String intro;

    @JsonIgnore
    @Version
    private Integer version;

    @JsonIgnore
    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS", timezone = "GMT+8")
    private LocalDateTime datetime;


    @Builder
    public DemoUserDO(Long id, String createBy, LocalDateTime createTime, String updateBy, LocalDateTime updateTime,
                      String tenantCode, String name, Integer age, OrderStatus status, String intro, Integer version, Boolean deleted,
                      LocalDateTime datetime) {

        super(id, createBy, createTime, updateBy, updateTime);

        this.tenantCode = tenantCode;
        this.name = name;
        this.age = age;
        this.status = status;
        this.intro = intro;
        this.version = version;
        this.deleted = deleted;
        this.datetime = datetime;
    }

}
