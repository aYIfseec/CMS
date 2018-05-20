package com.cms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cms.entity.Student;
import com.cms.entity.Teacher;
import com.cms.service.StudentService;
import com.cms.utils.MD5Util;
import com.cms.utils.StrUtil;
import com.cms.utils.page.Pagination;

@Controller
@RequestMapping(value="/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public String getStudentList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="20")int nums,
			@RequestParam(defaultValue="")String searchKey) {
		
		Pagination<Student> page = new Pagination<Student>();
		
		page.setTotalItemsCount(studentService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Student> list = studentService.getStudentList(page,searchKey);
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		return jsonStr;
	}
	
	
	/**
	 * 返回选修了我课程的学生列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/stulist")
	public String getMyStudentList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="20")int nums,
			@RequestParam(required=false) Integer cId, HttpSession session) {
		Teacher t = (Teacher) session.getAttribute(StrUtil.USER);
		Pagination<Student> page = new Pagination<Student>();
		
		page.setTotalItemsCount(studentService.getTotalItemsCountByTid(t.getId(), cId));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Student> list = studentService.getStudentListByTid(page, t.getId(), cId);
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	
	
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/studentAdd");
	}
	
	/**
	 * 增加，或者修改studnet
	 * @param opType
	 * @param stu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public String addStudent(@RequestParam(defaultValue="2") int opType, Student stu) {
		int res = 0;
		if (opType == 0) {
			try {
				stu.setPassword(stu.getPassword().toUpperCase());
				res = studentService.addStudent(stu);
			} catch (Exception e) {
				System.out.println("添加失败！学号重复！");
				return "添加失败！学号重复！";
			}
			if (res > 0)
				return StrUtil.RESULT_TRUE;
			return "添加失败";
		} else if (opType == 1) {
			stu.setPassword(null);
			res = studentService.updateStudent(stu);
			if (res > 0) return StrUtil.RESULT_TRUE;
			return "修改失败！";
		}
		return "error";
	}
	
	/**
	 * 重置密码
	 * @param stu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/resetPswd")
	public String resetPasswrd(String id) {
		Student stu = new Student();
		stu.setId(id);
		stu.setPassword(MD5Util.MD5("123456"));
		if (studentService.updateStudent(stu) > 0) return StrUtil.RESULT_TRUE;
		return "修改失败！";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String deleteStudnet(Student stu) {
		if (studentService.deleteStudent(stu) > 0) return StrUtil.RESULT_TRUE;
		return "删除失败！";
	}
	
	/**
	 * 批量删除
	 * @param stuIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public String deleteStudnetList(String stuIds) {
		List<String> list = new ArrayList<String>();
		try {
			String[] ids = stuIds.split(",");
			for (String id: ids) {
				list.add(id);
			}
			if (studentService.deleteStudent(list) > 0) {
				return StrUtil.RESULT_TRUE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败！参数出错！";//
		}
		return "删除失败！";
	}
	
	@ResponseBody
	@RequestMapping("/import")  
	public String impotr(HttpServletRequest request, MultipartFile file) {  
	     //获取上传的文件  
	     InputStream in = null;
		try {
			in = file.getInputStream();
			//数据导入  
			int res = studentService.importExcelInfo(in,file);
			if (res > 0) {
				return StrUtil.RETURN_JONS_PRE_STR+"0"
						+StrUtil.RETURN_JONS_MID_STR+"true"
						+StrUtil.RETURN_JONS_END_STR;
			} else {
				return StrUtil.RETURN_JONS_PRE_STR+"0"
						+StrUtil.RETURN_JONS_MID_STR+"false"
						+StrUtil.RETURN_JONS_END_STR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return StrUtil.RETURN_JONS_PRE_STR+"0"
			+StrUtil.RETURN_JONS_MID_STR+"error"
			+StrUtil.RETURN_JONS_END_STR;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	@RequestMapping(value="/courses")
	public ModelAndView toChoiceCoursePage() {
		return new ModelAndView("choiceCourse");
	}
}
