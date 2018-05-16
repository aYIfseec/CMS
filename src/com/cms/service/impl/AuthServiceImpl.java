package com.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.dao.AuthDao;
import com.cms.entity.Auth;
import com.cms.service.AuthService;
import com.cms.utils.page.Pagination;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;
	
	
	@Override
	public List<Auth> getMenuList(String userType) {
		return authDao.selectMenu(userType);
	}

	@Override
	public List<Auth> getUrlList(String userType) {
		return authDao.selectUrl(userType);
	}

	@Override
	public int getTotalItemsCount(String searchKey) {
		return authDao.getTotalItemsCount(searchKey);
	}

	@Override
	public List<Auth> getAuthList(Pagination<Auth> page,
			String searchKey) {
		return authDao.getAuthList(page,searchKey);
	}

	@Override
	public int update(Auth auth) {
		return authDao.updateByPrimaryKeySelective(auth);
	}

}
