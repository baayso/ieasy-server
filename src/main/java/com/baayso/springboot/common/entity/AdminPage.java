package com.baayso.springboot.common.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 管理系统分页实体。
 *
 * @author ChenFangjie (2016/5/19 16:31)
 * @since 1.0.0
 */
public class AdminPage<T> {

    public static final int    DEFAULT_PAGE_SIZE   = 10;
    public static final int    DEFAULT_PAGE_NUMBER = 10;
    public static final String DEFAULT_SORT_NAME   = "";
    public static final String DEFAULT_SORT_ORDER  = "ASC";

    // 页面传递的参数或者是配置的参数
    private Integer pageSize;           // 页大小（每页显示多少条记录）
    private Integer pageNumber;         // 当前页
    private String  sortName;           // 排序字段名称
    private String  sortOrder;          // 排序方式

    // 需要查询数据库
    private Long    total;              // 总记录数
    private List<T> rows;               // 当前页数据

    // 需要计算
    private Long pageCount;          // 总页数


    public AdminPage() {
        this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER, DEFAULT_SORT_NAME, DEFAULT_SORT_ORDER);
    }

    /**
     * @param pageSize 页大小（每页显示多少条记录）
     */
    public AdminPage(int pageSize) {
        this(pageSize, DEFAULT_PAGE_NUMBER, DEFAULT_SORT_NAME, DEFAULT_SORT_ORDER);
    }

    /**
     * @param pageSize   页大小（每页显示多少条记录）
     * @param pageNumber 当前页
     */
    public AdminPage(int pageSize, int pageNumber) {
        this(pageSize, pageNumber, DEFAULT_SORT_NAME, DEFAULT_SORT_ORDER);
    }

    /**
     * @param sortName 排序字段名称
     */
    public AdminPage(String sortName) {
        this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER, sortName, DEFAULT_SORT_ORDER);
    }

    /**
     * @param sortName  排序字段名称
     * @param sortOrder 排序方式
     */
    public AdminPage(String sortName, String sortOrder) {
        this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER, sortName, sortOrder);
    }

    /**
     * @param pageSize 页大小（每页显示多少条记录）
     * @param sortName 排序字段名称
     */
    public AdminPage(int pageSize, String sortName) {
        this(pageSize, DEFAULT_PAGE_NUMBER, sortName, DEFAULT_SORT_ORDER);
    }

    /**
     * @param pageSize  页大小（每页显示多少条记录）
     * @param sortName  排序字段名称
     * @param sortOrder 排序方式
     */
    public AdminPage(int pageSize, String sortName, String sortOrder) {
        this(pageSize, DEFAULT_PAGE_NUMBER, sortName, sortOrder);
    }

    /**
     * @param pageSize   页大小（每页显示多少条记录）
     * @param pageNumber 当前页
     * @param sortName   排序字段名称
     * @param sortOrder  排序方式
     */
    public AdminPage(int pageSize, int pageNumber, String sortName, String sortOrder) {
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


    public AdminPage<T> initBeforePage() {
        PageHelper.startPage(pageNumber, pageSize);

        if (StringUtils.isNotBlank(sortName)) {
            PageHelper.orderBy(sortName + " " + sortOrder);
        }

        return this;
    }

    public AdminPage<T> initAfterPage(PageInfo<T> pageInfo) {
        if (pageInfo != null) {
            this.total = pageInfo.getTotal();
            this.rows = pageInfo.getList();
            // this.pageCount = (recordCount + this.pageSize - 1) / this.pageSize;
            this.pageCount = (long) pageInfo.getPages();
        }

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

}
