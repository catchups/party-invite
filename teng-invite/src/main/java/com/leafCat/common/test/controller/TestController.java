package com.leafCat.common.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
    @RequestMapping("/moveXssTest.do")
    private ModelAndView moveXssTest(HttpServletRequest request) {
    	
        return new ModelAndView("test/xssTestInput");
    }
    
    @RequestMapping("/xssTest.do")
    private ModelAndView xssTest(HttpServletRequest request) {
    	String rtn= request.getParameter("test");
    	return new ModelAndView("test/xssTest", "val", rtn);
    }
    
    @RequestMapping("/test.do")
    private ModelAndView test(HttpServletRequest request) {

    	System.out.println("1111111111111111111111");

    	return null;
    }
    
}
