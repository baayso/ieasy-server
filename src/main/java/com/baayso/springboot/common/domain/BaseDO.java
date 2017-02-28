package com.baayso.springboot.common.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据对象基类。
 *
 * @author ChenFangjie (2016/4/13 10:42)
 * @since 1.0.0
 */
@Getter
@Setter
public class BaseDO extends IdEntity {

    private static final long serialVersionUID = 6787870565161459149L;

    /**
     * A String for {@code "0"}.
     *
     * @since 1.0.0
     */
    public static final String ZERO = "0";

    @JsonIgnore
    protected String createBy;                  // 记录创建人

    protected Date createTime;                  // 记录创建时间

    @JsonIgnore
    protected String modifyBy;                  // 记录修改人

    @JsonIgnore
    protected Date modifyTime;                  // 记录修改时间

    @JsonIgnore
    protected Integer modifyNum;                // 记录修改次数


    public void initBeforeAdd() {
        this.initBeforeAdd(ZERO, new Date());
    }

    public void initBeforeAdd(String actor) {
        this.initBeforeAdd(actor, new Date());
    }

    public void initBeforeAdd(Date date) {
        this.initBeforeAdd(ZERO, date);
    }

    public void initBeforeAdd(String actor, Date date) {
        setCreateBy(actor);
        setCreateTime(date);
        // setModifyBy(actor);
        // setModifyTime(date);
        // setModifyNum(0);
    }

    public void initBeforeUpdate() {
        this.initBeforeUpdate(ZERO, new Date());
    }

    public void initBeforeUpdate(String actor) {
        this.initBeforeUpdate(actor, new Date());
    }

    public void initBeforeUpdate(Date date) {
        this.initBeforeUpdate(ZERO, date);
    }

    public void initBeforeUpdate(String actor, Date date) {
        setModifyBy(actor);
        setModifyTime(date);
    }

    public void initBeforeDelete() {
        this.initBeforeDelete(ZERO, new Date());
    }

    public void initBeforeDelete(String actor) {
        this.initBeforeDelete(actor, new Date());
    }

    public void initBeforeDelete(Date date) {
        this.initBeforeDelete(ZERO, date);
    }

    public void initBeforeDelete(String actor, Date date) {
        setModifyBy(actor);
        setModifyTime(date);
    }

}
