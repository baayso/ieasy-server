package com.baayso.springboot.common.domain;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分页展示对象。
 *
 * @author ChenFangjie (2016/4/18 15:00)
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -8033284604162270759L;

    private List<T> list;     // 数据
    private Long    total;    // 总记录数
    private Long    size;     // 每页记录数
    private Long    current;  // 当前页
    private Long    pages;    // 总页数


    public static <T> PageVO<T> creator(IPage<T> page) {
        return PageVO.<T>builder() //
                .list(page.getRecords()) //
                .total(page.getTotal()) //
                .size(page.getSize()) //
                .current(page.getCurrent()) //
                .pages(page.getPages()) //
                .build();
    }

}
