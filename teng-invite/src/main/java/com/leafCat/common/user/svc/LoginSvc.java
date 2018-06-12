package com.leafCat.common.user.svc;

import javax.servlet.http.HttpSession;

import com.leafCat.common.user.vo.LoginVO;

public interface LoginSvc {
	
	public String doLogin(LoginVO inVO, HttpSession session) throws Exception;
	
	public void doLogout(HttpSession session) throws Exception;
	

}
