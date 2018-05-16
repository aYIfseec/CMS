package com.cms.service;

import com.cms.entity.Admin;

public interface AdminService {
	public Admin selectAdmin(Admin admin);

	public int update(Admin admin);
}
