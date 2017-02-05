package com.wx.weixin.web.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.wx.weixin.web.interceptor.JSSDKInterceptor;

@Before(JSSDKInterceptor.class)
public class IndexController extends Controller {
	
	public void index() {
		render("index.jsp");
	}
	
}
