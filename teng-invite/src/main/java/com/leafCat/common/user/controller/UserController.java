package com.leafCat.common.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.leafCat.common.user.svc.UserMgmtSvc;
import com.leafCat.common.user.vo.UserVO;
import com.leafCat.common.util.StringUtils;

@RestController
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
    @Autowired
    UserMgmtSvc userMgmtSvc;
	
    @RequestMapping("/moveUserList.do")
    private ModelAndView moveUserList() throws Exception {
    	ModelAndView mv = new ModelAndView("user/userList");
    	return mv;
    }

    @RequestMapping("/moveUserList2.do")
    private ModelAndView moveUserList2() throws Exception {
    	ModelAndView mv = new ModelAndView("user/userList2");
    	return mv;
    }
    
    @RequestMapping(value = "/userMgmt/getUserList.do", method=RequestMethod.POST)
    private ModelAndView getUserList(@ModelAttribute UserVO userVO, 
    		HttpServletRequest request) throws Exception {
		/*	author		:	hyucksu.jang
		 * 	date		:	2017.11.02
		 * 	description	:	dataTables 플러그인 serverside processing 기능 추가에 따른 수정
		 */
		String orderCol = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");
		String orderColNmRegacy = request.getParameter("columns["+orderCol+"][data]");
		String orderColNm = "";
		if(orderColNmRegacy != null){
			orderColNm =  StringUtils.camelToUnderbar(request.getParameter("columns["+orderCol+"][data]"));
		}
		
		ModelAndView mv = new ModelAndView("jsonView");
		userVO.setOrderBy(orderDir);
		userVO.setOrderId(orderColNm);
		
		List<UserVO> userList = userMgmtSvc.selectUserList(userVO);
		int totalCnt = userMgmtSvc.selectUserListCount(userVO);
		
		mv.addObject("recordsTotal", totalCnt);
		mv.addObject("recordsFiltered", totalCnt);
		mv.addObject("data", userList);
		
		return mv;
		
    }

}
