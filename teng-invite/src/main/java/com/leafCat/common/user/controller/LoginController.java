package com.leafCat.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leafCat.common.user.svc.LoginSvc;
import com.leafCat.common.user.svc.UserMgmtSvc;
import com.leafCat.common.user.vo.LoginVO;
import com.leafCat.common.user.vo.UserVO;
import com.leafCat.common.util.ClientUtils;
import com.leafCat.common.util.ObjectUtils;
import com.leafCat.common.util.StaticStringUtil;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
    @Autowired
    UserMgmtSvc userMgmtSvc;
    
    @Autowired
    LoginSvc loginSvc;
	
    @RequestMapping("/moveLogin.go")
    private ModelAndView moveLogin(@ModelAttribute LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserVO sessionVO = (UserVO) session.getAttribute("loginUser");
		
		if(!ObjectUtils.isEmpty(sessionVO)){
			return new ModelAndView("redirect:/moveMain.do");
		}
		
        return new ModelAndView("login/login");
    }
    
    @RequestMapping("/moveRegister.go")
    private ModelAndView moveRegister() throws Exception {
    	return new ModelAndView("login/register");
    }
    
    @RequestMapping("/logout.do")
    private void doLogout(HttpSession session, HttpServletResponse response) throws Exception {
    	loginSvc.doLogout(session);
    	response.sendRedirect("/moveLogin.go");
    }
    
    @RequestMapping(value="/login.go", method=RequestMethod.POST)
    private ModelAndView doLogin(@ModelAttribute LoginVO loginVO, BindingResult result, HttpSession session,
			RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.out.println("11111111111111111111111");
    	/*
    	 * 로그인 처리 SVC 호출
    	 * */
    	loginVO.setLoginIp(ClientUtils.getRemoteIP(request));
    	String loginRslt = loginSvc.doLogin(loginVO, session);
    	
    	/*
    	 * 분기
    	 * Login성공
    	 * */
    	loginVO.setLoginIp(ClientUtils.getRemoteIP(request));
    	if(loginRslt.equals(StaticStringUtil.USER_LOGIN_SUCCESS)){
    		logger.debug("=============> " + session.getAttribute("loginUser").toString());
    		return new ModelAndView("redirect:/moveMain.do");
    	}else{
			return new ModelAndView("login/login", "loginErr", loginRslt);
    	}
    	
    }
    
    

}
