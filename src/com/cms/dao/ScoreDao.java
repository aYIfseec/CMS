package com.cms.dao;

import java.util.List;

import com.cms.dto.ScoreVo;
import com.cms.entity.Course;
import com.cms.entity.Score;
import com.cms.utils.page.Pagination;

public interface ScoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

	int delete(Score score);

	int update(List<Score> scoreList);

	List<Course> getCourseList(Pagination<Course> page, String id, Integer result);

	int getTotalItemsCount(String id, Integer result);

	int getTotalItemsCountForExport(ScoreVo scoreVo);

	List<ScoreVo> getScoreListForExport(Pagination<ScoreVo> page,
			ScoreVo scoreVo);
}