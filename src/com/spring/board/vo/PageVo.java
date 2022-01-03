package com.spring.board.vo;

import java.util.Arrays;
import java.util.List;

public class PageVo {
	
	private int pageNo = 0;
	
	private String[] searchType;
	private String[] searchName;
	private String[] codeId;
	
	public String[] getCodeId() {
		return codeId;
	}
	public void setCodeId(String[] codeId) {
		this.codeId = codeId;
	}
	public String[] getSearchType() {
		return searchType;
	}
	public void setSearchType(String[] searchType2) {
		this.searchType = searchType2;
	}
	public String[] getSearchName() {
		return searchName;
	}
	public void setSearchName(String[] searchName) {
		this.searchName = searchName;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	@Override
	public String toString() {
		return "PageVo [pageNo=" + pageNo + ", searchType=" + Arrays.toString(searchType) + ", searchName="
				+ Arrays.toString(searchName) + "]";
	}
	
	
	
}
