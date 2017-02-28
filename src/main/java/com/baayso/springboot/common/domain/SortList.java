package com.baayso.springboot.common.domain;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;

/**
 * 排序集合。
 *
 * @author ChenFangjie (2016/4/21 11:07)
 * @since 1.0.0
 */
public class SortList extends ArrayList<SortDTO> {

    private static final long serialVersionUID = 2686281079576407145L;

    public static final String DEFAULT_SORT_ORDER = "ASC";


    public static SortList add(String sortField, String sortDirection) {
        SortList orderBeans = null;

        if (StringUtils.isNotBlank(sortField)) {
            orderBeans = new SortList();

            SortDTO sort = new SortDTO();
            sort.setSortName(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortField));
            sort.setSortOrder(StringUtils.isNotBlank(sortDirection) ? sortDirection : DEFAULT_SORT_ORDER);

            orderBeans.add(sort);
        }

        return orderBeans;
    }

    public static SortList add(SortDTO... sort) {
        SortList orderBeans = new SortList();
        orderBeans.addAll(Arrays.asList(sort));

        return orderBeans;
    }

}
