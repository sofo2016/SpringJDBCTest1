/*
 * Copyright (C) 2017 CETITI
 * File Name:Utils.java
 * Description:
 * Author:Chensj
 * Create Date:2017-12-20
 * Modifier:Chensj
 * Modify Date:2017-12-20
 * Bugzilla Id: 
 * Modify Content:
 */
package com.cetiti.dim.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PageInfo<T> implements Serializable
{
    private static final long serialVersionUID = -6347437149349807086L;
    private Integer total;        //总记录数
    private List<T> list;    //结果集transient 
    private Integer pageNum;    // 第几页
    private Integer pageSize;    // 每页记录数
    private Integer pages;        // 总页数
    
    public PageInfo(List<T> list,Integer total,Pagination page) {
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = total;
        if (this.pageSize != null)
        {
        	this.pages = Integer.valueOf((int)Math.ceil(total.floatValue()/this.pageSize.floatValue()));
        }
        this.list = list;
    }
    public PageInfo(List<T> list,Long total,Pagination page) {
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = Integer.parseInt(total.toString());
        if (this.pageSize != null)
        {
            this.pages = Integer.valueOf((int)Math.ceil(total.floatValue()/this.pageSize.floatValue()));
        }
        this.list = list;
    }
    
     public PageInfo(List<T> list,BigDecimal total,Pagination page) {
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = total.intValue();
        if (this.pageSize != null)
        {
            this.pages = Integer.valueOf((int)Math.ceil(total.floatValue()/this.pageSize.floatValue()));
        }
        this.list = list;
    }
    
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}