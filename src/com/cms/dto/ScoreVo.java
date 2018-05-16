package com.cms.dto;

import com.cms.entity.Course;

public class ScoreVo extends Course{
	
	private String stuId;
	private String stuName;
	private String searchKey;
	private Integer isPass;
	private String major;
	private String grade;
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	
	@Override
	public String toString() {
		return "ScoreVo [stuId=" + stuId + ", stuName=" + stuName
				+ ", searchKey=" + searchKey + ", isPass=" + isPass
				+ ", major=" + major + ", grade=" + grade + "]";
	}
}
