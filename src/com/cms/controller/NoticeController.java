package com.cms.controller;

import java.io.File;
import java.io.IOException;
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
import com.cms.entity.Notice;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.NoticeService;
import com.cms.utils.StrUtil;
import com.cms.utils.page.Pagination;

@Controller
@RequestMapping(value="notice")
public class NoticeController {
	
	private static final Integer AUTH_A = 3;
	private static final Integer AUTH_T = 2;
	private static final Integer AUTH_S = 1;
	
	
	@Autowired
	private NoticeService noticeService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public String getNoticeList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="20")int nums,
			@RequestParam(defaultValue="")String searchKey,
			HttpSession session) {
		
		Pagination<Student> page = new Pagination<Student>();
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Notice> list = new ArrayList<Notice>();
		
		User user = (User) session.getAttribute(StrUtil.USER);
		Integer auth = null;
		if (user.getUserType().equals(StrUtil.ADMIN)) {
			auth = AUTH_A;
		} else if (user.getUserType().equals(StrUtil.TEACHER)) {
			auth = AUTH_T;
		} else if (user.getUserType().equals(StrUtil.STUDENT)) {
			auth = AUTH_S;
		}
		page.setTotalItemsCount(noticeService.getTotalItemsCount(auth, searchKey));
		list = noticeService.getNoticeList(page, auth, searchKey);
		
		String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ page.getTotalItemsCount()
				+ StrUtil.RETURN_JONS_MID_STR
				+ JSON.toJSONString(list)
				+ StrUtil.RETURN_JONS_END_STR;
		return jsonStr;
	}
	
	/**
	 * 查看公告
	 * @param nId
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/info")
	public ModelAndView showNoticeInfo(HttpSession session, Integer nId, ModelAndView mav) {
		User user = (User) session.getAttribute(StrUtil.USER);
		Integer auth = null;
		if (user.getUserType().equals(StrUtil.ADMIN)) {
			auth = AUTH_A;
		} else if (user.getUserType().equals(StrUtil.TEACHER)) {
			auth = AUTH_T;
		} else if (user.getUserType().equals(StrUtil.STUDENT)) {
			auth = AUTH_S;
		}
		Notice notice = noticeService.getNotice(nId);
		//无权限查看
		if (auth < notice.getAuth()) {
			return new ModelAndView("404");
		}
		mav = new ModelAndView("notice");
		mav.addObject("notice", notice);
		return mav;
	}
	@RequestMapping(value="/look")
	public ModelAndView showNotice(){
		return new ModelAndView("notice");
	}
	
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("noticeAdd");
	}
	/**
	 * 增加，或者修改notice
	 * @param opType
	 * @param notice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public String addNotice(@RequestParam(defaultValue="2")Integer opType, Notice notice) {
		int res = 0;
		if (opType == 0) {
			try {
				res = noticeService.addNotice(notice);
			} catch (Exception e) {
				System.out.println("添加失败！");
				return "添加失败！";
			}
			if (res > 0)
				return StrUtil.RESULT_TRUE;
			return "添加失败";
		} else if (opType == 1) {
			res = noticeService.updateNotice(notice);
			if (res > 0) return StrUtil.RESULT_TRUE;
			return "修改失败！";
		}
		return "error";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String deleteNotice(Notice notice) {
		if (noticeService.deleteNotice(notice) > 0) return StrUtil.RESULT_TRUE;
		return "删除失败！";
	}
	
	/**
	 * 批量删除
	 * @param stuIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public String deleteNoticeList(String nIds) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] ids = nIds.split(",");
			for (String id: ids) {
				list.add(Integer.parseInt(id));
			}
			if (noticeService.deleteNotice(list) > 0) {
				return StrUtil.RESULT_TRUE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败！参数出错！";//
		}
		return "删除失败！";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/uploadImg")
	public String uploadImg(MultipartFile file,HttpServletRequest request) throws IOException{  
        System.out.println("comming!");
        String path = request.getSession().getServletContext().getRealPath("/images");  
        System.out.println("path>>"+path);
        
        String fileName = file.getOriginalFilename();
        System.out.println("fileName>>"+fileName);
        fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        fileName = fileName + System.currentTimeMillis();
        System.out.println("fileName>>"+fileName);
        File dir = new File(path, fileName);
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
//      MultipartFile自带的解析方法  
        file.transferTo(dir);  
        
        String jsonStr = StrUtil.RETURN_JONS_PRE_STR
				+ null
				+ StrUtil.RETURN_JONS_MID_STR
				+ "{\"src\":\"" + "/CMS/images/" + fileName + "\"}"
				+ StrUtil.RETURN_JONS_END_STR;
		return jsonStr;
    }  
}
