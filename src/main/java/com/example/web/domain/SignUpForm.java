package com.example.web.domain;

import java.io.Serializable;

public class SignUpForm  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String userName;
	private String email;
	private String password1;
	private String password2;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
