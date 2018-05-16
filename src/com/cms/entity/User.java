package com.cms.entity;

import java.util.List;

public class User {
	
	private String userType;
	
	private List<Auth> menuList;
	private List<Auth> urlList;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public List<Auth> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Auth> menuList) {
		this.menuList = menuList;
	}

	public List<Auth> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<Auth> urlList) {
		this.urlList = urlList;
	}
}