package com.leafCat.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.leafCat.common.user.vo.UserVO;
import com.leafCat.common.util.ObjectUtils;

@Component
public class CertificationInterceptor implements HandlerInterceptor{

	private static Logger logger = Logger.getLogger(CertificationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVO loginVO = (UserVO) session.getAttribute("loginUser");
		logger.debug("session check");
		
		if(ObjectUtils.isEmpty(loginVO)){
			logger.debug("session expired");
			response.sendRedirect("/moveLogin.go");
			return false;
		}else{
			logger.debug("session ok");
			session.setMaxInactiveInterval(30*60);
			return true;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
