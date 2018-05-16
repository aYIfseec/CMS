package com.cms.dao;

import java.util.List;

import com.cms.entity.BaseCourse;
import com.cms.utils.page.Pagination;

public interface BaseCourseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseCourse record);

    int insertSelective(BaseCourse record);

    BaseCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCourse record);

    int updateByPrimaryKey(BaseCourse record);

	int getTotalItemsCount(String searchKey);

	List<BaseCourse> getBaseCourse(Pagination<BaseCourse> page, String searchKey);

	List<BaseCourse> getBaseCourseForSelect(String searchKey);

	int deleteInList(List<Integer> list);

	int insertBatch(List<BaseCourse> list);
}