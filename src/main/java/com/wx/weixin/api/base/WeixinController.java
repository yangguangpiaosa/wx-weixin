package com.wx.weixin.api.base;

import com.jfinal.core.Controller;

public class WeixinController extends Controller {
	
	public void index() {
		setAttr("msg", "微信公众号服务端连接正常....");
		render("weixin.jsp");
	}
	
	public void weui() {
		render("weui/example/index.jsp");
	}
	
	public void demo() {
		render("test/test.jsp");
	}
	
}
