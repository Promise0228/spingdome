package com.util;

import java.util.List;

/**
 * 分页实体类
 * 
 * @filename PageBean.java
 * @author hm
 * @date 2018年6月29日下午2:36:03
 */
public class PageBean {
	
	// 每页显示条数
	private int page = 5;
	// 总条数
	private int totalNum = 0;
	// 当前页码
	private int currNo = 1;
	// 总页数
	private int totalPage = 0;
	// 查询下标位置，limit 中的第一个参数
	private int pageIndex = 0;
	// 存储返回的集合对象
	private List<?> resultList;
	// 请求地址, user/list.action
	private String url;
	// 请求参数(搜索条件参数的封装), &userCode=admin&userType=1&userState=1
	private String params;

	public PageBean() {

	}

	//每页显示条数，当前页
	public PageBean(int page, int currNo) {
		this.page = page;
		this.currNo = currNo;
	}

	//每页显示条数，总页数，当前页数，返回的集合对象
	public PageBean(int page, int totalNum, int currNo, List<?> resultList,
			String url, String params) {
		super();
		this.page = page;
		this.totalNum = totalNum;
		this.currNo = currNo;
		this.resultList = resultList;
		this.url = url;
		this.params = params;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	// 当前页码
	public int getCurrNo() {
		if (currNo == 0) {
			currNo = 1;
		}
		return currNo;
	}

	public void setCurrNo(int currNo) {
		this.currNo = currNo;
	}

	// 总页数
	public int getTotalPage() {
		totalPage = (totalNum % page == 0) ? (totalNum / page)
				: (totalNum / page) + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	// 查询下标位置
	public int getPageIndex() {
		return page * (currNo - 1);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", totalNum=" + totalNum
				+ ", currNo=" + currNo + ", totalPage=" + totalPage
				+ ", pageIndex=" + pageIndex + ", resultList=" + resultList
				+ ", url=" + url + ", params=" + params + "]";
	}

	
	
}
