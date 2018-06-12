package com.leafCat.common.user.svc;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafCat.common.user.mapper.LoginMapper;
import com.leafCat.common.user.vo.LoginVO;
import com.leafCat.common.user.vo.UserVO;
import com.leafCat.common.util.ObjectUtils;
import com.leafCat.common.util.StaticStringUtil;

@Service
public class LoginSvcImpl implements LoginSvc {
	
	private static Logger logger = Logger.getLogger(LoginSvcImpl.class);
	
	@Autowired
	LoginMapper loginMapper;

	@Override
	public String doLogin(LoginVO inVO, HttpSession session) throws Exception {
		
		//1.이메일로 사용자 조회
		UserVO loginUser = loginMapper.selectOneUserForLogin(inVO);
		
		//TODO 비밀번호 암호화 추가해야함
		
		/*
		 * 분기
		 * 없는 이메일 - 존재하지 않는 사용자 반환
		 * */
		if(ObjectUtils.isEmpty(loginUser)){
			logger.debug("loginUser -----------> 존재하지 않는 사용자");
			return StaticStringUtil.USER_NOT_EXIST; 
		}
		
		/*
		 * 분기
		 * 비밀번호 오류 횟수 N회 이상이면 바로 튕김
		 * */
		if(loginUser.getPasswordErrCnt() >= 5){
			logger.debug("loginUser -----------> 계정 잠김");
			return StaticStringUtil.USER_LOCKED;
		}
		
		/*
		 * 분기
		 * 비밀번호 틀림
		 * */
		if(!inVO.getPassword().equals(loginUser.getPassword())){

			//TODO 비밀번호 오류 횟수 1회 증가
			logger.debug("loginUser -----------> 비밀번호 틀림");
			
			//비밀번호 오류 횟수 N회 이상 
			//TODO N회를 property로 빼기
			if(loginUser.getPasswordErrCnt() >= 5 ){
				//TODO 계정 잠금 (USER_STAT_CD = LO)
				logger.debug("loginUser -----------> 계정 잠김");
				return StaticStringUtil.USER_LOCKED;
			}
			
			return StaticStringUtil.USER_PASSWORD_ERR;
			
		}
		
		/*
		 * 로그인 로그 쌓기
		 * */
		inVO.setUserId(loginUser.getUserId());
		loginMapper.insertLoginHistory(inVO);
		
		/*
		 * 로그인 사용자 정보 세션에 저장
		 * */
		//개인정보는 제외
		loginUser.setPassword("");
		session.setAttribute("loginUser", loginUser);
		session.setMaxInactiveInterval(30*60);
		
		logger.debug("loginUser -----------> 로그인 성공 : " + loginUser.getUserName());
		
		return StaticStringUtil.USER_LOGIN_SUCCESS;
	}

	@Override
	public void doLogout(HttpSession session) throws Exception {
		
		session.invalidate();
	}
	
	

}
