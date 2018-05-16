package com.cms.utils;

public class StrUtil {
	
	/**
	 * 为配合前端框架table的数据格式
	 */
	public static final String RETURN_JONS_PRE_STR = "{\"code\":0,\"msg\":\"\",\"count\":";
	public static final String RETURN_JONS_MID_STR = ",\"data\":";
	public static final String RETURN_JONS_END_STR = "}";
	
	
	/**
	 * 反馈到前端的
	 */
	public static final String RESULT_TRUE = "true";
	public static final String RESULT_FALSE = "false";
	public static final String CODE_ERROR = "code_error";
	
	/**
	 * 用户类型
	 */
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String TEACHER = "teacher";
	public static final String STUDENT = "student";
	
	public static final String VERIFY_CODE = "verifyCode";
	
	
	
}