package com.imhipi.app.tms.model;

import java.util.HashMap;
import java.util.Map;

public class Pagination {

	private int currentPage;
	
	private int pageCount = 10;
	
	private Long total;
	
	private Map condition = new HashMap();
	
	private Map sort = new HashMap();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Map getCondition() {
		return condition;
	}

	public void setCondition(Map condition) {
		this.condition = condition;
	}

	public Map getSort() {
		return sort;
	}

	public void setSort(Map sort) {
		this.sort = sort;
	}
	
	public int getSkip() {
		return (this.currentPage - 1) * this.pageCount;
	}

}
