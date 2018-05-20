package com.cms.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.dao.CourseDao;
import com.cms.dao.ScoreDao;
import com.cms.dto.ExcelBean;
import com.cms.dto.ScoreVo;
import com.cms.entity.Course;
import com.cms.entity.Score;
import com.cms.service.ScoreService;
import com.cms.utils.ExcelUtil;
import com.cms.utils.page.Pagination;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreDao scoreDao;

	@Autowired
	CourseDao courseDao;

	@Override
	public int choiceCourse(Score score) {
		// 选课过程为一个事务
		Course c = courseDao.selectByPrimaryKey(score.getcId());
		if (c.getStudentNum() == c.getChoiceNum()) {
			// 此课程学生人数已满，选课失败
			return 0;
		}
		System.out.println(c.toString());
		// 对所选课程中，choiceNum（已选人数+1）
		c.setChoiceNum(c.getChoiceNum() + 1);
		courseDao.updateByPrimaryKeySelective(c);
		return scoreDao.insertSelective(score);
	}

	@Override
	public int deleteScore(Score score) {
		Course c = courseDao.selectByPrimaryKey(score.getcId());
		c.setChoiceNum(c.getChoiceNum() - 1);
		courseDao.updateByPrimaryKeySelective(c);
		return scoreDao.delete(score);
	}

	@Override
	public int updateScore(Score score) {
		return scoreDao.updateByPrimaryKeySelective(score);
	}

	@Override
	public int updateScore(List<Score> scoreList) {
		return scoreDao.update(scoreList);
	}

	@Override
	public int getTotalItemsCount(String id, Integer result) {
		return scoreDao.getTotalItemsCount(id, result);
	}

	@Override
	public List<Course> getCourseList(Pagination<Course> page, String id,
			Integer result) {
		return scoreDao.getCourseList(page, id, result);
	}

	@Override
	public int getTotalItemsCount(ScoreVo scoreVo) {
		return scoreDao.getTotalItemsCountForExport(scoreVo);
	}

	@Override
	public List<ScoreVo> getScoreList(Pagination<ScoreVo> page, ScoreVo scoreVo) {
		return scoreDao.getScoreListForExport(page, scoreVo);
	}

	@Override
	public XSSFWorkbook exportExcelInfo(ScoreVo scoreVo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {  
	    //根据条件查询数据，把数据装载到一个list中  
	    List<ScoreVo> list = scoreDao.getScoreListForExport(null, scoreVo);
	    List<ExcelBean> excel=new ArrayList<ExcelBean>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<Integer,List<ExcelBean>>();
	    XSSFWorkbook xssfWorkbook=null;
	    //设置标题栏  
	    excel.add(new ExcelBean("时间","startDate",0));  
	    excel.add(new ExcelBean("学号","stuId",0));  
	    excel.add(new ExcelBean("姓名","stuName",0));  
	    excel.add(new ExcelBean("专业","major",0));  
	    excel.add(new ExcelBean("班级","grade",0));  
	    excel.add(new ExcelBean("课程名","courseName",0));  
	    excel.add(new ExcelBean("任课教师","teacherName",0));  
	    excel.add(new ExcelBean("考核方式","testMode",0));  
	    excel.add(new ExcelBean("成绩","score",0));  
	    excel.add(new ExcelBean("结果","result",0));  
	    map.put(0, excel);
	    //调用ExcelUtil的方法  
	    xssfWorkbook = ExcelUtil.createExcelFile(ScoreVo.class, list, map, "sheet1");  
	    return xssfWorkbook;  
	}
}
