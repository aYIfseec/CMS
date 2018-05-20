package com.cms.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cms.entity.Student;
import com.cms.utils.page.Pagination;

public interface StudentService {

	int getTotalItemsCount(String searchKey);

	List<Student> getStudentList(Pagination<Student> page, String searchKey);

	Student selectStudent(Student stu);

	int addStudent(Student stu) throws Exception;

	int updateStudent(Student stu);
	
	int deleteStudent(Student stu);
	
	int deleteStudent(List<String> ids);

	int importExcelInfo(InputStream in, MultipartFile file) throws Exception;

	int getTotalItemsCountByTid(String id, Integer cId);

	List<Student> getStudentListByTid(Pagination<Student> page, String id,
			Integer cId);

}
