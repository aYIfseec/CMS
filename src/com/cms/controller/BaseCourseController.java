package com.cms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cms.entity.BaseCourse;
import com.cms.service.BaseCourseService;
import com.cms.utils.StrUtil;
import com.cms.utils.page.Pagination;

@Controller
@RequestMapping(value="/basecourse")
public class BaseCourseController {
	
	@Autowired
	private BaseCourseService baseCourseService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public String getBaseCourseList(@RequestParam(defaultValue="0")int curr,@RequestParam(defaultValue="10")int nums,
			@RequestParam(defaultValue="")String searchKey) {
		Pagination<BaseCourse> page = new Pagination<BaseCourse>();
		
		page.setTotalItemsCount(baseCourseService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		List<BaseCourse> list = baseCourseService.getBaseCourse(page, searchKey);
		
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR + page.getTotalItemsCount() 
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list) + StrUtil.RETURN_JONS_END_STR;
		
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping(value="/listForSelect")
	public String getBaseCourseListForSelect(@RequestParam(defaultValue="") String searchKey) {
		List<BaseCourse> list = baseCourseService.getBaseCourseForSelect(searchKey);
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR + list.size() 
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list) + StrUtil.RETURN_JONS_END_STR;
		return jsonStr;
	}
    
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/baseCourseAdd");
	}
	
	/**
	 * 增加，或者修改BaseCourse
	 * @param BaseCourse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public String addBaseCourse(BaseCourse baseCourse) {
		int res = 0;
		if (baseCourse.getId() == null || baseCourse.getId().equals("")) {
			try {
				res = baseCourseService.addBaseCourse(baseCourse);
			} catch (Exception e) {
				System.out.println("添加失败！");
				return "添加失败！";
			}
			if (res > 0)
				return StrUtil.RESULT_TRUE;
			return "添加失败";
		} else {
			res = baseCourseService.updateBaseCourse(baseCourse);
			if (res > 0) return StrUtil.RESULT_TRUE;
			return "修改失败！";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String deleteStudnet(BaseCourse t) {
		if (baseCourseService.deleteBaseCourse(t) > 0) return StrUtil.RESULT_TRUE;
		return "删除失败！";
	}
	
	/**
	 * 批量删除
	 * @param tIds
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
			if (baseCourseService.deleteBaseCourse(list) > 0) {
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
			int res = baseCourseService.importExcelInfo(in,file);
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
	
}
