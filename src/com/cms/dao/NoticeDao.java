package com.cms.dao;

import java.util.List;

import com.cms.entity.Notice;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;

public interface NoticeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

	int deleteBatch(List<Integer> list);

	List<Notice> getNotice(Pagination<Student> page, Integer auth,
			String searchKey);

	int getTotalItemsCount(Integer authA, String searchKey);
}