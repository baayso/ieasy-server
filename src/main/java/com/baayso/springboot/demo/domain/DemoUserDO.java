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
import com.gitee.easyopen.doc.DataType;
import com.gitee.easyopen.doc.annotation.ApiDocField;

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
    private Long tenantId; // 租户ID

    @ApiDocField(description = "用户名称")
    private String name;

    @ApiDocField(description = "用户年龄", dataType = DataType.INT)
    private Integer age;

    @ApiDocField(description = "状态", dataType = DataType.INT, enumClass = OrderStatus.class, example = "128")
    private OrderStatus status;

    @ApiDocField(description = "用户简介")
    private String intro;

    @JsonIgnore
    @Version
    private Integer version;

    @JsonIgnore
    @TableLogic
    private Boolean isDeleted;

    @TableField(exist = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS", timezone = "GMT+8")
    private LocalDateTime datetime;


    @Builder
    public DemoUserDO(Long id, String createBy, LocalDateTime createTime, String modifyBy, LocalDateTime modifyTime,
                      Long tenantId, String name, Integer age, OrderStatus status, String intro, Integer version, Boolean isDeleted,
                      LocalDateTime datetime) {

        super(id, createBy, createTime, modifyBy, modifyTime);

        this.tenantId = tenantId;
        this.name = name;
        this.age = age;
        this.status = status;
        this.intro = intro;
        this.version = version;
        this.isDeleted = isDeleted;
        this.datetime = datetime;
    }

}
