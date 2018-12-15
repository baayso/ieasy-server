package com.baayso.springboot.user.domain;

import java.time.LocalDateTime;

import com.baayso.springboot.common.domain.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 数据对象：角色。
 *
 * @author ChenFangjie (2018/12/14 20:42)
 * @since 3.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("role")
public class RoleDO extends BaseDO {

    private static final long serialVersionUID = -5931155799860749663L;

    @ApiDocField(description = "名称")
    private String name;

    @ApiDocField(description = "编码")
    private String code;

    @ApiDocField(description = "描述")
    private String descr;


    @Builder
    public RoleDO(Long id, String createBy, LocalDateTime createTime, String modifyBy, LocalDateTime modifyTime,
                  String name, String code, String descr) {

        super(id, createBy, createTime, modifyBy, modifyTime);

        this.name = name;
        this.code = code;
        this.descr = descr;
    }

}
