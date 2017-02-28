package com.baayso.springboot.common.domain;

import lombok.Data;

/**
 * 排序实体。
 *
 * @author ChenFangjie (2016/4/21 11:06)
 * @since 1.0.0
 */
@Data
public class SortBean {

    private String sortName;           // 排序字段名称
    private String sortOrder;          // 排序方式

}
