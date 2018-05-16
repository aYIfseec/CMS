package com.cms.entity;


public class Admin extends User{
	private Integer id;
	private String username;
	private String password;
	private String nickname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + "]";
	}
	
}
