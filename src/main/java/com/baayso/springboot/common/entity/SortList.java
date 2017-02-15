package com.baayso.springboot.common.entity;

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
public class SortList extends ArrayList<SortBean> {

    private static final long serialVersionUID = 2686281079576407145L;

    public static final String DEFAULT_SORT_ORDER = "ASC";


    public static SortList add(String sortField, String sortDirection) {
        SortList orderBeans = null;

        if (StringUtils.isNotBlank(sortField)) {
            orderBeans = new SortList();

            SortBean orderBean = new SortBean();
            orderBean.setSortName(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortField));
            orderBean.setSortOrder(StringUtils.isNotBlank(sortDirection) ? sortDirection : DEFAULT_SORT_ORDER);

            orderBeans.add(orderBean);
        }

        return orderBeans;
    }

    public static SortList add(SortBean... sortBean) {
        SortList orderBeans = new SortList();
        orderBeans.addAll(Arrays.asList(sortBean));

        return orderBeans;
    }

}
