package com.cms.service;

import com.cms.entity.User;

public interface Login {

	/**
	 * 登录验证方法
	 * @return
	 */
	public User loginValidate(String username, String password);
}
