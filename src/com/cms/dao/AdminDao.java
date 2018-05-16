package com.cms.dao;

import com.cms.entity.Admin;

public interface AdminDao {
	public Admin select(Admin admin);

	public int updateByPrimaryKeySelective(Admin admin);
}