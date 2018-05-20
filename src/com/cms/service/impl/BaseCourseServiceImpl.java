package com.cms.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cms.dao.BaseCourseDao;
import com.cms.entity.BaseCourse;
import com.cms.service.BaseCourseService;
import com.cms.utils.ExcelUtil;
import com.cms.utils.page.Pagination;

@Service
@Transactional
public class BaseCourseServiceImpl implements BaseCourseService {

	@Autowired
	BaseCourseDao baseCourseDao;
	
	@Override
	public int getTotalItemsCount(String searchKey) {
		return baseCourseDao.getTotalItemsCount(searchKey);
	}

	@Override
	public List<BaseCourse> getBaseCourse(Pagination<BaseCourse> page,
			String searchKey) {
		return baseCourseDao.getBaseCourse(page, searchKey);
	}

	@Override
	public List<BaseCourse> getBaseCourseForSelect(String searchKey) {
		return baseCourseDao.getBaseCourseForSelect(searchKey);
	}

	@Override
	public int addBaseCourse(BaseCourse baseCourse) {
		return baseCourseDao.insertSelective(baseCourse);
	}

	@Override
	public int updateBaseCourse(BaseCourse baseCourse) {
		return baseCourseDao.updateByPrimaryKeySelective(baseCourse);
	}

	@Override
	public int deleteBaseCourse(BaseCourse t) {
		return baseCourseDao.deleteByPrimaryKey(t.getId());
	}

	@Override
	public int deleteBaseCourse(List<Integer> list) {
		return baseCourseDao.deleteInList(list);
	}

	@Override
	public int importExcelInfo(InputStream in, MultipartFile file) throws Exception {
		List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,
				file.getOriginalFilename());
		List<BaseCourse> list = new ArrayList<BaseCourse>();
		for (List<Object> ob: listob) {
			BaseCourse course = new BaseCourse();
			course.setName(String.valueOf(ob.get(0)));
			course.setSynopsis(String.valueOf(ob.get(1)));
			
			list.add(course);
		}
		return baseCourseDao.insertBatch(list);
	}

}
