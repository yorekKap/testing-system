package com.testing.system.web.paginator;

import java.util.List;

public class PageInfo<T> {

	private List<T> records;
	private int pageIndex;
	private int numOfPages;

	public PageInfo(List<T> records, int pageIndex, int numOfPages) {
		this.records = records;
		this.pageIndex = pageIndex;
		this.numOfPages = numOfPages;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getNumOfPages() {
		return numOfPages;
	}

	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}



}
