package com.cms.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.dto.ScoreVo;
import com.cms.entity.Course;
import com.cms.entity.Score;
import com.cms.entity.Student;
import com.cms.service.ScoreService;
import com.cms.utils.StrUtil;
import com.cms.utils.page.Pagination;

@Controller
@RequestMapping(value="/score")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public String getScoreList(Integer curr, Integer nums, ScoreVo scoreVo) {
		System.out.println(scoreVo);
		Pagination<ScoreVo> page = new Pagination<ScoreVo>();
		page.setTotalItemsCount(scoreService.getTotalItemsCount(scoreVo));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<ScoreVo> list = scoreService.getScoreList(page, scoreVo);
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response, ScoreVo scoreVo)
			throws ClassNotFoundException, IntrospectionException,
			IllegalAccessException, ParseException, InvocationTargetException {
			
		response.reset(); // 清除buffer缓存
		// 设置文件名
		response.setHeader("Content-Disposition", "attachment;filename="
				+ System.currentTimeMillis() + ".xls");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		XSSFWorkbook workbook = null;
		// 导出Excel对象
		workbook = scoreService.exportExcelInfo(scoreVo);
		OutputStream output;
		try {
			output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			bufferedOutPut.flush();
			workbook.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 学生查成绩列表
	 * @param curr
	 * @param nums
	 * @param searchKey
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/stuScore")
	public String getScoreList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="20")int nums,
			HttpSession session, Integer result) {
		Student stu = (Student) session.getAttribute(StrUtil.USER);
		
		Pagination<Course> page = new Pagination<Course>();
		
		page.setTotalItemsCount(scoreService.getTotalItemsCount(stu.getId(), result));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Course> list = scoreService.getCourseList(page, stu.getId(), result);
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	
	/**
	 * 学生选课
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/choiceCourse")
	public String choiceCourse(HttpSession session,
			@RequestParam(defaultValue="")Integer id) {
		if (id != null) {
			Student s = (Student) session.getAttribute(StrUtil.USER);
			Score score = new Score();
			score.setsId(s.getId());
			score.setcId(id);
			int res = scoreService.choiceCourse(score);
			if (res > 0) return StrUtil.RESULT_TRUE;
			else return StrUtil.RESULT_FALSE;
		}
		return "参数错误！";
	}
	
	
	/**
	 * 学生取消选课
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public String deleteCourse(@RequestParam(defaultValue="")Integer id, HttpSession session) {
		Student stu = (Student) session.getAttribute(StrUtil.USER);
		Score s = new Score();
		s.setcId(id);
		s.setsId(stu.getId());
		if (id != null) {
			int res = scoreService.deleteScore(s);
			if (res > 0) return StrUtil.RESULT_TRUE;
			else return StrUtil.RESULT_FALSE;
		}
		return "参数错误！";
	}
	
	/**
	 * 评分
	 * @param score
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public String updateScore(Score score) {
		int res = scoreService.updateScore(score);
		if (res > 0) return StrUtil.RESULT_TRUE;
		else return StrUtil.RESULT_FALSE;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateList")
	public String updateScoreList(String scoreListStr) {
		List<Score> scoreList = JSON.parseArray(scoreListStr, Score.class);
		System.out.println(scoreList);
		int res = scoreService.updateScore(scoreList);
		if (res > 0) return StrUtil.RESULT_TRUE;
		else return StrUtil.RESULT_FALSE;
	}
}
