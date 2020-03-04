package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Page
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 11:31 2019/4/23
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page<T> implements Serializable {
    //已知数据
    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;   //每页显示的条数
    @NotNull(message = "currentPage不能为空")
    private Integer currentPage;    //当前页
    //需要计算
    private Integer total;  //总数
    private Integer totalPage;  //总页数
    //显示的数据
    private List list;
    private T data;

    private Page() {
    }

    private Page(Integer currentPage, Integer pageSize, Integer total, Integer totalPage, List list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
        this.list = list;
    }

    public static Page createPage(Integer current, Integer pageSize, Integer total, List list) {
        Integer totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return new Page(current, pageSize, total, totalPage, list);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
