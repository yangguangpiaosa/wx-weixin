package com.wx.weixin.api.menu;

import com.jfinal.kit.JsonKit;

public class MenuManager {
	
	public static String jsonMenu() {
		return JsonKit.toJson(getMenu()).toString();
	}
	
	private static Menu getMenu() {
		ClickButton text = new ClickButton();
		text.setName("文本消息");
		text.setType("click");
		text.setKey("text_1");
		
		ClickButton news = new ClickButton();
		news.setName("图文消息");
		news.setType("click");
		news.setKey("news_1");
		
		ViewButton view = new ViewButton();
		view.setName("跳转网页");
		view.setType("view");
		view.setUrl("http://wxdev.iask.in/weixin/oauth2/oauth?t=y");
		
		ComButton cb = new ComButton();
		cb.setName("组合菜单");
		cb.setSub_button(new Button[] {text, news, view});
		
		ViewButton weui = new ViewButton();
		weui.setName("官方示例");
		weui.setType("view");
		weui.setUrl("http://wxdev.iask.in/weixin/weui");
		
		ViewButton demo = new ViewButton();
		demo.setName("测试示例");
		demo.setType("view");
		demo.setUrl("http://wxdev.iask.in/weixin/demo");
		
		ComButton weuicb = new ComButton();
		weuicb.setName("WeUI");
		weuicb.setSub_button(new Button[] {weui, demo});
		
		ClickButton about = new ClickButton();
		about.setName("关于我");
		about.setType("click");
		about.setKey("about_me");
		
		Menu menu = new Menu();
		menu.setButton(new Button[] {cb, weuicb, about});
		
		return menu;
	}

}
