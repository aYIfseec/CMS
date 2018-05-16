package com.cms.service;

import java.util.List;

import com.cms.entity.Notice;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;

public interface NoticeService {

	int getTotalItemsCount(Integer authA, String searchKey);

	List<Notice> getNoticeList(Pagination<Student> page, Integer auth,
			String searchKey);

	int deleteNotice(Notice notice);

	int deleteNotice(List<Integer> list);

	int addNotice(Notice notice);

	int updateNotice(Notice notice);

	Notice getNotice(Integer nId);
	
}
