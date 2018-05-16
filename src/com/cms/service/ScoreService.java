package com.cms.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cms.dto.ScoreVo;
import com.cms.entity.Course;
import com.cms.entity.Score;
import com.cms.utils.page.Pagination;

public interface ScoreService {

	int choiceCourse(Score score);

	int updateScore(Score score);

	int deleteScore(Score s);

	int updateScore(List<Score> scoreList);

	int getTotalItemsCount(String string, Integer result);

	List<Course> getCourseList(Pagination<Course> page, String string, Integer result);

	int getTotalItemsCount(ScoreVo scoreVo);

	List<ScoreVo> getScoreList(Pagination<ScoreVo> page, ScoreVo scoreVo);

	XSSFWorkbook exportExcelInfo(ScoreVo scoreVo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException;

}
