package com.cms.dao;

import java.util.List;

import com.cms.entity.Teacher;
import com.cms.utils.page.Pagination;

public interface TeacherDao {
	
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

	Teacher selectTeacher(Teacher teacher);

	int getTotalItemsCount(String searchKey);

	int deleteInList(List<String> list);

	int insertBatch(List<Teacher> list);

	List<Teacher> getTeacher(Pagination<Teacher> page, String searchKey);

	List<Teacher> getTeacherForSelect(String searchKey);

}