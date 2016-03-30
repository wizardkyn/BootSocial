package com.example.web.domain;

import java.io.Serializable;

public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String passWd;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
}
