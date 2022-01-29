package com.baayso.springboot.common.domain;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.baayso.commons.utils.Validator;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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

    private static final long serialVersionUID = 1L;

    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    protected String creator;                   // 记录创建人

    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Validator.DEFAULT_DATETIME_PATTERN, timezone = "GMT+8")
    protected LocalDateTime createTime;         // 记录创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    protected String updater;                   // 记录修改人

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Validator.DEFAULT_DATETIME_PATTERN, timezone = "GMT+8")
    protected LocalDateTime updateTime;         // 记录修改时间

    public BaseDO() {
    }

    public BaseDO(Long id, String creator, LocalDateTime createTime, String updater, LocalDateTime updateTime) {
        super(id);

        this.creator = creator;
        this.createTime = createTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    @Deprecated
    public BaseDO initBeforeAdd() {
        return this.initBeforeAdd(StringUtils.EMPTY, LocalDateTime.now());
    }

    @Deprecated
    public BaseDO initBeforeAdd(String actor) {
        return this.initBeforeAdd(actor, LocalDateTime.now());
    }

    @Deprecated
    public BaseDO initBeforeAdd(LocalDateTime dateTime) {
        return this.initBeforeAdd(StringUtils.EMPTY, dateTime);
    }

    @Deprecated
    public BaseDO initBeforeAdd(String actor, LocalDateTime dateTime) {
        setCreator(actor);
        setCreateTime(dateTime);
        setUpdater(actor);
        setUpdateTime(dateTime);

        return this;
    }

    @Deprecated
    public BaseDO initBeforeUpdate() {
        return this.initBeforeUpdate(StringUtils.EMPTY, LocalDateTime.now());
    }

    @Deprecated
    public BaseDO initBeforeUpdate(String actor) {
        return this.initBeforeUpdate(actor, LocalDateTime.now());
    }

    @Deprecated
    public BaseDO initBeforeUpdate(LocalDateTime dateTime) {
        return this.initBeforeUpdate(StringUtils.EMPTY, dateTime);
    }

    @Deprecated
    public BaseDO initBeforeUpdate(String actor, LocalDateTime dateTime) {
        setUpdater(actor);
        setUpdateTime(dateTime);

        return this;
    }

    @Deprecated
    public BaseDO initBeforeDelete() {
        return this.initBeforeDelete(StringUtils.EMPTY, LocalDateTime.now());
    }

    @Deprecated
    public BaseDO initBeforeDelete(String actor) {
        return this.initBeforeDelete(actor, LocalDateTime.now());
    }

    @Deprecated
    public BaseDO initBeforeDelete(LocalDateTime dateTime) {
        return this.initBeforeDelete(StringUtils.EMPTY, dateTime);
    }

    @Deprecated
    public BaseDO initBeforeDelete(String actor, LocalDateTime dateTime) {
        setUpdater(actor);
        setUpdateTime(dateTime);

        return this;
    }

}
