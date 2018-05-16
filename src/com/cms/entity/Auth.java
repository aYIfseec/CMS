package com.cms.entity;

public class Auth {
    @Override
	public String toString() {
		return "Auth [id=" + id + ", name=" + name + ", url=" + url
				+ ", adminAuth=" + adminAuth + ", teacherAuth=" + teacherAuth
				+ ", studentAuth=" + studentAuth + "]";
	}

	private Integer id;

    private String name;

    private String url;

    private Byte adminAuth;

    private Byte teacherAuth;

    private Byte studentAuth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getAdminAuth() {
        return adminAuth;
    }

    public void setAdminAuth(Byte adminAuth) {
        this.adminAuth = adminAuth;
    }

    public Byte getTeacherAuth() {
        return teacherAuth;
    }

    public void setTeacherAuth(Byte teacherAuth) {
        this.teacherAuth = teacherAuth;
    }

    public Byte getStudentAuth() {
        return studentAuth;
    }

    public void setStudentAuth(Byte studentAuth) {
        this.studentAuth = studentAuth;
    }
}