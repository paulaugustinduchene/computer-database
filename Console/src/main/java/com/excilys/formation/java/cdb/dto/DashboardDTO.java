package com.excilys.formation.java.cdb.dto;

public class DashboardDTO {
	
	private String search;
	private String pageLength;
	private String page;
	private String count;
	private String maxpage;
	private String List;
	private String orderAsc;
	private String listCompanies;

	
	
	public String getOrderAsc() {
		return orderAsc;
	}
	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getPageLength() {
		return pageLength;
	}
	public void setPageLength(String pageLength) {
		this.pageLength = pageLength;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(String maxpage) {
		this.maxpage = maxpage;
	}
	public String getList() {
		return List;
	}
	public void setList(String list) {
		List = list;
	}
	public String getListCompanies() {
		return listCompanies;
	}
	public void setListCompanies(String listCompanies) {
		this.listCompanies = listCompanies;
	}

}
