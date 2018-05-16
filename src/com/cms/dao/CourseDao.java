package com.cms.dao;

import java.util.List;

import com.cms.entity.Course;
import com.cms.utils.page.Pagination;

public interface CourseDao {
	int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

	List<Course> getCourseList(Pagination<Course> page, String searchKey);

	int getTotalItemsCount(String searchKey);

	int deleteInList(List<Integer> list);

	int getTotalItemsCountByTid(String id);

	List<Course> getCourseListByTid(Pagination<Course> page, String id);

	int getTotalItemsCountBySid(int isAll, String searchKey, String id);

	List<Course> getCourseListBySid(Pagination<Course> page, int isAll,  String searchKey,
			String id);
}