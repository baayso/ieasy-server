package com.baayso.springboot.common.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 分页实体。
 *
 * @author ChenFangjie (2016/4/18 15:00)
 * @since 1.0.0
 */
public class Page<T> {

    public static final int    DEFAULT_PAGE_SIZE   = 10;
    public static final int    DEFAULT_PAGE_NUMBER = 1;
    public static final String DEFAULT_SORT_NAME   = "";
    public static final String DEFAULT_SORT_ORDER  = "ASC";

    // 页面传递的参数或者是配置的参数
    private Integer pageSize;           // 页大小（每页显示多少条记录）
    private Integer pageNumber;         // 当前页
    private String  sortName;           // 排序字段名称
    private String  sortOrder;          // 排序方式

    // 需要查询数据库
    private Long    recordCount;        // 总记录数
    private List<T> dataList;           // 当前页数据

    // 需要计算
    private Long pageCount;          // 总页数


    public Page() {
        this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER, DEFAULT_SORT_NAME, DEFAULT_SORT_ORDER);
    }

    /**
     * @param pageSize 页大小（每页显示多少条记录）
     */
    public Page(int pageSize) {
        this(pageSize, DEFAULT_PAGE_NUMBER, DEFAULT_SORT_NAME, DEFAULT_SORT_ORDER);
    }

    /**
     * @param pageSize   页大小（每页显示多少条记录）
     * @param pageNumber 当前页
     */
    public Page(int pageSize, int pageNumber) {
        this(pageSize, pageNumber, DEFAULT_SORT_NAME, DEFAULT_SORT_ORDER);
    }

    /**
     * @param sortName 排序字段名称
     */
    public Page(String sortName) {
        this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER, sortName, DEFAULT_SORT_ORDER);
    }

    /**
     * @param sortName  排序字段名称
     * @param sortOrder 排序方式
     */
    public Page(String sortName, String sortOrder) {
        this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER, sortName, sortOrder);
    }

    /**
     * @param pageSize 页大小（每页显示多少条记录）
     * @param sortName 排序字段名称
     */
    public Page(int pageSize, String sortName) {
        this(pageSize, DEFAULT_PAGE_NUMBER, sortName, DEFAULT_SORT_ORDER);
    }

    /**
     * @param pageSize  页大小（每页显示多少条记录）
     * @param sortName  排序字段名称
     * @param sortOrder 排序方式
     */
    public Page(int pageSize, String sortName, String sortOrder) {
        this(pageSize, DEFAULT_PAGE_NUMBER, sortName, sortOrder);
    }

    /**
     * @param pageSize   页大小（每页显示多少条记录）
     * @param pageNumber 当前页
     * @param sortName   排序字段名称
     * @param sortOrder  排序方式
     */
    public Page(int pageSize, int pageNumber, String sortName, String sortOrder) {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (StringUtils.isBlank(sortName)) {
            sortName = DEFAULT_SORT_NAME;
        }

        if (StringUtils.isBlank(sortOrder)) {
            sortOrder = DEFAULT_SORT_ORDER;
        }

        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortName);
        this.sortOrder = sortOrder;
    }


    public Page<T> initBeforePage() {
        PageHelper.startPage(this.pageNumber, this.pageSize);

        if (StringUtils.isNotBlank(this.sortName)) {
            PageHelper.orderBy(this.sortName + " " + this.sortOrder);
        }

        return this;
    }

    public Page<T> initAfterPage(PageInfo<T> pageInfo) {
        if (pageInfo != null) {
            this.recordCount = pageInfo.getTotal();
            this.dataList = pageInfo.getList();
            // this.pageCount = (this.recordCount + this.pageSize - 1) / this.pageSize;
            this.pageCount = (long) pageInfo.getPages();
        }

        return this;
    }

    public Page<T> initAfterPage(long recordCount, List<T> list) {
        this.recordCount = recordCount;
        this.dataList = list;
        this.pageCount = (this.recordCount + this.pageSize - 1) / this.pageSize;

        return this;
    }

    // ===================================================================================

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

}
