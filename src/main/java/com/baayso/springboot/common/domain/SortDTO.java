package com.baayso.springboot.common.domain;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;

import lombok.Data;

/**
 * 排序数据传输对象。
 *
 * @author ChenFangjie (2016/4/21 11:06)
 * @since 1.0.0
 */
@Data
public class SortDTO {

    public static final String DEFAULT_SORT_ORDER = "ASC";

    private String sortName;           // 排序字段名称
    private String sortOrder;          // 排序方式

    public SortDTO() {
    }

    public SortDTO(String sortName, String sortOrder) {
        this.sortName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortName);
        this.sortOrder = StringUtils.isNotBlank(sortOrder) ? sortOrder : DEFAULT_SORT_ORDER;
    }

}
