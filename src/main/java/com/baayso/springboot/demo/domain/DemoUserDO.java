package com.baayso.springboot.demo.domain;

import java.time.LocalDateTime;

import com.baayso.springboot.common.domain.BaseDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    private Long        tenantId; // 租户ID
    private String      name;
    private Integer     age;
    private OrderStatus status;
    private String      intro;

    @Version
    private Integer version;

    @TableLogic
    private Boolean isDeleted;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
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
