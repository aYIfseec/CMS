package com.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.dao.AdminDao;
import com.cms.entity.Admin;
import com.cms.entity.User;
import com.cms.service.AdminService;
import com.cms.service.Login;

@Service
@Transactional
public class AdminServiceImpl implements AdminService, Login{

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin selectAdmin(Admin admin) {
		return adminDao.select(admin);
	}

	@Override
	public User loginValidate(String username, String password) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin = selectAdmin(admin);
		if(admin != null) admin.setUserType("admin");
		return admin;
	}

	@Override
	public int update(Admin admin) {
		return adminDao.updateByPrimaryKeySelective(admin);
	}

}
