package com.cms.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cms.entity.Teacher;
import com.cms.utils.page.Pagination;

public interface TeacherService {

	Teacher selectTeacher(Teacher teacher);

	int getTotalItemsCount(String searchKey);

	int addTeacher(Teacher stu);

	int updateTeacher(Teacher stu);

	int deleteTeacher(Teacher t);

	int deleteTeacher(List<String> list);

	int importExcelInfo(InputStream in, MultipartFile file) throws Exception;

	List<Teacher> getTeacher(Pagination<Teacher> page, String searchKey);

	List<Teacher> getTeacherForSelect(String searchKey);

}
