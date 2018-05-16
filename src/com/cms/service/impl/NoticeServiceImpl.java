package com.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.dao.NoticeDao;
import com.cms.entity.Notice;
import com.cms.entity.Student;
import com.cms.service.NoticeService;
import com.cms.utils.page.Pagination;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public int getTotalItemsCount(Integer authA, String searchKey) {
		return noticeDao.getTotalItemsCount(authA, searchKey);
	}

	@Override
	public List<Notice> getNoticeList(Pagination<Student> page, Integer auth,
			String searchKey) {
		return noticeDao.getNotice(page, auth, searchKey);
	}

	@Override
	public int deleteNotice(Notice notice) {
		return noticeDao.deleteByPrimaryKey(notice.getId());
	}

	@Override
	public int deleteNotice(List<Integer> list) {
		return noticeDao.deleteBatch(list);
	}

	@Override
	public int addNotice(Notice notice) {
		return noticeDao.insert(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeDao.updateByPrimaryKeySelective(notice);
	}

	@Override
	public Notice getNotice(Integer nId) {
		return noticeDao.selectByPrimaryKey(nId);
	}

}
