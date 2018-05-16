package com.cms.service;

import java.util.List;

import com.cms.entity.Auth;
import com.cms.utils.page.Pagination;


public interface AuthService {

	List<Auth> getMenuList(String userType);

	List<Auth> getUrlList(String userType);

	int getTotalItemsCount(String searchKey);

	List<Auth> getAuthList(Pagination<Auth> page, String searchKey);

	int update(Auth auth);

}
