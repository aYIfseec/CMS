package com.cms.dao;

import java.util.List;

import com.cms.entity.Student;
import com.cms.utils.page.Pagination;

public interface StudentDao {
    int deleteByPrimaryKey(String id);

    int insert(Student record) throws Exception;

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

	List<Student> selectBySearchKey(Pagination<Student> page, String searchKey);

	int getTotalItemsCount(String searchKey);

	Student select(Student stu);

	int deleteInList(List<String> ids);

	int insertBatch(List<Student> list);

	int getTotalItemsCountByTid(String id, Integer cId);

	List<Student> getStudentListByTid(Pagination<Student> page, String id,
			Integer cId);
}