package com.cms.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cms.dao.StudentDao;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.Login;
import com.cms.service.StudentService;
import com.cms.utils.ExcelUtil;
import com.cms.utils.MD5Util;
import com.cms.utils.page.Pagination;

@Service
@Transactional
public class StudentServiceImpl implements StudentService, Login {

	@Autowired
	private StudentDao studentDao;

	@Override
	public int getTotalItemsCount(String searchKey) {
		return studentDao.getTotalItemsCount(searchKey);
	}

	@Override
	public List<Student> getStudentList(Pagination<Student> page,
			String searchKey) {
		return studentDao.selectBySearchKey(page, searchKey);
	}

	@Override
	public Student selectStudent(Student stu) {

		return studentDao.select(stu);
	}

	@Override
	public int addStudent(Student stu) throws Exception {
		return studentDao.insert(stu);
	}

	@Override
	public int updateStudent(Student stu) {
		return studentDao.updateByPrimaryKeySelective(stu);
	}

	@Override
	public int deleteStudent(Student stu) {
		return studentDao.deleteByPrimaryKey(stu.getId());
	}

	@Override
	public int deleteStudent(List<String> list) {
		return studentDao.deleteInList(list);
	}

	@Override
	public int importExcelInfo(InputStream in, MultipartFile file)
			throws Exception {
		List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,
				file.getOriginalFilename());
		List<Student> list = new ArrayList<Student>();
		// 遍历listob数据，把数据放到List中
		for (List<Object> ob: listob) {
			Student Student = new Student();
			// 通过遍历实现把每一列封装成一个model中
			Student.setId(String.valueOf(ob.get(0)));
			Student.setPassword(MD5Util.MD5((String)ob.get(1)));
			Student.setName(String.valueOf(ob.get(2)));
			Student.setSex(String.valueOf(ob.get(3)));
			Student.setAdmissionDate(String.valueOf(ob.get(4)));
			Student.setMajor(String.valueOf(ob.get(5)));
			Student.setGrade(String.valueOf(ob.get(6)));
			Student.setEducation(String.valueOf(ob.get(7)));
			
			list.add(Student);
		}
		return studentDao.insertBatch(list);
	}
	
	@Override
	public User loginValidate(String username, String password) {
		Student student = new Student();
		student.setId(username);
		student.setPassword(password);
		student = selectStudent(student);
		if(student != null) student.setUserType("student");
		return student;
	}

	@Override
	public int getTotalItemsCountByTid(String id, Integer cId) {
		return studentDao.getTotalItemsCountByTid(id, cId);
	}

	@Override
	public List<Student> getStudentListByTid(Pagination<Student> page,
			String id, Integer cId) {
		return studentDao.getStudentListByTid(page, id, cId);
	}
}
