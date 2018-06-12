package com.leafCat.common.user.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafCat.common.user.mapper.UserListMapper;
import com.leafCat.common.user.vo.UserVO;

@Service
public class UserMgmtSvcImpl implements UserMgmtSvc {

	@Autowired
    UserListMapper userListMapper;


	@Override
	public String getNow() throws Exception {
		return userListMapper.getNow();
	}

	@Override
	public UserVO selectOneUser() throws Exception {
		return userListMapper.selectOneUser(new UserVO());
	}
	
	@Override
	public List<UserVO> selectUserList(UserVO inVO) throws Exception {
		return userListMapper.selectUserList(inVO);
	}

	@Override
	public int selectUserListCount(UserVO inVO) throws Exception {
		// TODO Auto-generated method stub
		return  userListMapper.selectUserListCount(inVO).getTotal();
	}

	
}
