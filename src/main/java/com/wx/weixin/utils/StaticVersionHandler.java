package com.wx.weixin.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;

public class StaticVersionHandler extends Handler {

private String version;
	
	public StaticVersionHandler() {
		version = "version";
	}
	
	public StaticVersionHandler(String version) {
		if (StrKit.isBlank(version))
			throw new IllegalArgumentException("version can not be blank.");
		this.version = version;
	}
	
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		request.setAttribute(version, df.format(new Date()));
		next.handle(target, request, response, isHandled);
	}

}
