package com.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cms.entity.Course;
import com.cms.entity.Student;
import com.cms.entity.Teacher;
import com.cms.service.CourseService;
import com.cms.utils.StrUtil;
import com.cms.utils.page.Pagination;

@Controller
@RequestMapping(value="/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public String getCourseList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="20")int nums,
			@RequestParam(defaultValue="")String searchKey) {
		
		Pagination<Course> page = new Pagination<Course>();
		
		page.setTotalItemsCount(courseService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Course> list = courseService.getCourseList(page,searchKey);
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	
	/**
	 * 返回教师自己教的课程列表
	 */
	@ResponseBody
	@RequestMapping(value="/getMyCourse")
	public String getMyCourse(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="10")int nums, HttpSession session) {
		
		Pagination<Course> page = new Pagination<Course>();
		Teacher t = (Teacher) session.getAttribute(StrUtil.USER);
		page.setTotalItemsCount(courseService.getTotalItemsCountByTid(t.getId()));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Course> list = courseService.getCourseListByTid(page, t.getId());
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	/**
	 * 返回可选课程列表（可选：人数未满、课程开始时间在当前时间之后）
	 * @param curr
	 * @param nums
	 * @param searchKey
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/choiceList")
	public String getCourseChoiceList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="30")int nums, @RequestParam(defaultValue="1") int isAll,
			@RequestParam(defaultValue="")String searchKey, HttpSession session) {
		Pagination<Course> page = new Pagination<Course>();
		String sId = ((Student) session.getAttribute(StrUtil.USER)).getId();
		
		page.setTotalItemsCount(courseService.getTotalItemsCountBySid(isAll, searchKey, sId));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Course> list = courseService.getCourseListBySid(page, isAll, searchKey, sId);
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		System.out.println(isAll+"sid"+sId);
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("courseAdd");
	}
	
	/**
	 * 增加，或者修改Course
	 * @param Course
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public String addCourse(Course course) {
		int res = 0;
		if (course.getId() == null || course.getId().equals("")) {
			try {
				res = courseService.addCourse(course);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("添加失败！");
				return "添加失败！";
			}
			if (res > 0)
				return StrUtil.RESULT_TRUE;
			return "添加失败";
		} else  {
			res = courseService.updateCourse(course);
			if (res > 0) return StrUtil.RESULT_TRUE;
			return "修改失败！";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/complete")
	public String complete(Course course) {
		int res = courseService.completeCourse(course);
		if (res > 0) return StrUtil.RESULT_TRUE;
		return "操作失败！";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String deleteStudnet(Course c) {
		if (courseService.deleteCourse(c) > 0) return StrUtil.RESULT_TRUE;
		return "删除失败！";
	}
	
	/**
	 * 批量删除
	 * @param cIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public String deleteStudnetList(String cIds) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] ids = cIds.split(",");
			for (String id: ids) {
				list.add(Integer.parseInt(id));
			}
			if (courseService.deleteCourse(list) > 0) {
				return StrUtil.RESULT_TRUE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败！参数出错！";//
		}
		return "删除失败！";
	}
}
