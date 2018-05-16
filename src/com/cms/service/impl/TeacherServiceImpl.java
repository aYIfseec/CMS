package com.cms.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cms.dao.TeacherDao;
import com.cms.entity.Teacher;
import com.cms.entity.User;
import com.cms.service.Login;
import com.cms.service.TeacherService;
import com.cms.utils.ExcelUtil;
import com.cms.utils.MD5Util;
import com.cms.utils.page.Pagination;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService, Login{

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Teacher selectTeacher(Teacher teacher) {
		return teacherDao.selectTeacher(teacher);
	}

	@Override
	public int getTotalItemsCount(String searchKey) {
		return teacherDao.getTotalItemsCount(searchKey);
	}

	@Override
	public int addTeacher(Teacher t) {
		return teacherDao.insert(t);
	}

	@Override
	public int updateTeacher(Teacher t) {
		return teacherDao.updateByPrimaryKeySelective(t);
	}

	@Override
	public int deleteTeacher(Teacher t) {
		return teacherDao.deleteByPrimaryKey(t.getId());
	}

	@Override
	public int deleteTeacher(List<String> list) {
		return teacherDao.deleteInList(list);
	}

	@Override
	public int importExcelInfo(InputStream in, MultipartFile file) throws Exception {
		List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,
				file.getOriginalFilename());
		List<Teacher> list = new ArrayList<Teacher>();
		// 遍历listob数据，把数据放到List中
		for (List<Object> ob: listob) {
			Teacher t = new Teacher();
			// 通过遍历实现把每一列封装成一个model中
			t.setId(String.valueOf(ob.get(0)));
			t.setPassword(MD5Util.MD5((String)ob.get(1)));
			t.setName(String.valueOf(ob.get(2)));
			t.setSynopsis(String.valueOf(ob.get(3)));
			
			list.add(t);
		}
		return teacherDao.insertBatch(list);
	}

	@Override
	public List<Teacher> getTeacher(Pagination<Teacher> page, String searchKey) {
		return teacherDao.getTeacher(page, searchKey);
	}

	@Override
	public List<Teacher> getTeacherForSelect(String searchKey) {
		return teacherDao.getTeacherForSelect(searchKey);
	}
	
	
	@Override
	public User loginValidate(String username, String password) {
		Teacher teacher = new Teacher();
		teacher.setId(username);
		teacher.setPassword(password);
		teacher = selectTeacher(teacher);
		if(teacher != null) teacher.setUserType("teacher");
		return teacher;
	}

}
