package com.baayso.springboot.common.domain;

import java.time.LocalDateTime;

import com.baayso.commons.utils.Validator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 数据对象基类。
 *
 * @author ChenFangjie (2016/4/13 10:42)
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseDO extends IdEntity {

    private static final long serialVersionUID = -3694675102885188955L;

    /**
     * A String for {@code "0"}.
     *
     * @since 1.0.0
     */
    public static final String ZERO = "0";


    @JsonIgnore
    protected String createBy;                  // 记录创建人

    @JsonFormat(pattern = Validator.DEFAULT_DATETIME_PATTERN)
    protected LocalDateTime createTime;         // 记录创建时间

    @JsonIgnore
    protected String modifyBy;                  // 记录修改人

    @JsonFormat(pattern = Validator.DEFAULT_DATETIME_PATTERN)
    protected LocalDateTime modifyTime;         // 记录修改时间


    public BaseDO(Long id, String createBy, LocalDateTime createTime, String modifyBy, LocalDateTime modifyTime) {
        super(id);

        this.createBy = createBy;
        this.createTime = createTime;
        this.modifyBy = modifyBy;
        this.modifyTime = modifyTime;
    }


    public void initBeforeAdd() {
        this.initBeforeAdd(ZERO, LocalDateTime.now());
    }

    public void initBeforeAdd(String actor) {
        this.initBeforeAdd(actor, LocalDateTime.now());
    }

    public void initBeforeAdd(LocalDateTime dateTime) {
        this.initBeforeAdd(ZERO, dateTime);
    }

    public void initBeforeAdd(String actor, LocalDateTime dateTime) {
        setCreateBy(actor);
        setCreateTime(dateTime);
        setModifyBy(actor);
        setModifyTime(dateTime);
    }

    public void initBeforeUpdate() {
        this.initBeforeUpdate(ZERO, LocalDateTime.now());
    }

    public void initBeforeUpdate(String actor) {
        this.initBeforeUpdate(actor, LocalDateTime.now());
    }

    public void initBeforeUpdate(LocalDateTime dateTime) {
        this.initBeforeUpdate(ZERO, dateTime);
    }

    public void initBeforeUpdate(String actor, LocalDateTime dateTime) {
        setModifyBy(actor);
        setModifyTime(dateTime);
    }

    public void initBeforeDelete() {
        this.initBeforeDelete(ZERO, LocalDateTime.now());
    }

    public void initBeforeDelete(String actor) {
        this.initBeforeDelete(actor, LocalDateTime.now());
    }

    public void initBeforeDelete(LocalDateTime dateTime) {
        this.initBeforeDelete(ZERO, dateTime);
    }

    public void initBeforeDelete(String actor, LocalDateTime dateTime) {
        setModifyBy(actor);
        setModifyTime(dateTime);
    }

}
