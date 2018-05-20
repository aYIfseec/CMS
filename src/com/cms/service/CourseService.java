package com.cms.service;

import java.util.List;

import com.cms.entity.Course;
import com.cms.utils.page.Pagination;

public interface CourseService {

	int getTotalItemsCount(String searchKey);

	List<Course> getCourseList(Pagination<Course> page, String searchKey);

	int addCourse(Course course);

	int updateCourse(Course course);

	int deleteCourse(Course c);

	int deleteCourse(List<Integer> list);

	int getTotalItemsCountByTid(String id);

	List<Course> getCourseListByTid(Pagination<Course> page, String id);

	int getTotalItemsCountBySid(int isAll, String searchKey, String id);

	List<Course> getCourseListBySid(Pagination<Course> page, int isAll, String searchKey,
			String id);

	int completeCourse(Course course);
}
