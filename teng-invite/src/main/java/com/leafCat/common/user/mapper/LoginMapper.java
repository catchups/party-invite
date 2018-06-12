package com.leafCat.common.user.mapper;

import com.leafCat.common.user.vo.LoginVO;
import com.leafCat.common.user.vo.UserVO;

public interface LoginMapper {

	public UserVO selectOneUserForLogin(LoginVO inVO) throws Exception;
	
	public int insertLoginHistory(LoginVO inVO) throws Exception;
	
}
