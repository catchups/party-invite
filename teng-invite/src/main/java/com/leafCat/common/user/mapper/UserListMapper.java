package com.leafCat.common.user.mapper;

import java.util.List;

import com.leafCat.common.user.vo.UserVO;

public interface UserListMapper {
	
	 public String getNow() throws Exception;
	 
	 public UserVO selectOneUser(UserVO inVO) throws Exception;
	 
	 public List<UserVO> selectUserList(UserVO inVO) throws Exception;
	 
	 public UserVO selectUserListCount(UserVO inVO) throws Exception;
	 
}
