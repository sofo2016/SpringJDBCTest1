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

public class Pagination{
	protected Integer pageSize;
	protected Integer pageNum;

	public Integer getStartPage() {
		if (pageNum != null)
		{
			return (pageNum - 1) * pageSize + 1;
		}
		return null;
	}

	public Integer getEndPage() {
		if (pageSize != null)
		{
			return getStartPage() + pageSize - 1;
		}
		return null;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
}
