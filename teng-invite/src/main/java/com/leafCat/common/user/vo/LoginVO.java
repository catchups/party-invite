package com.leafCat.common.user.vo;

import com.leafCat.common.main.vo.AbstractVO;

public class LoginVO extends AbstractVO {
	
	
	private String emailAddr;
	private String password;
	
	private int userId;
	private String loginIp;
	
	

	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@Override
	public String toString() {
		return "LoginVO [emailAddr=" + emailAddr + ", password=" + password + ", userId=" + userId + ", loginIp=" + loginIp
				+ "]";
	}


}
