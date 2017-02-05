package com.wx.weixin.web.interceptor;

import java.util.UUID;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.JsTicket;
import com.jfinal.weixin.sdk.api.JsTicketApi;
import com.jfinal.weixin.sdk.api.JsTicketApi.JsApiType;
import com.wx.weixin.utils.WeixinUtil;

public class JSSDKInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		inv.invoke();
		Controller controller = inv.getController();
		ApiConfigKit.setThreadLocalApiConfig(WeixinUtil.getApiConfig());
		JsTicket jsApiTicket = JsTicketApi.getTicket(JsApiType.jsapi);
		String jsapi_ticket = jsApiTicket.getTicket();
		String nonce_str = createNonceStr();
		// 注意 URL 一定要动态获取，不能 hardcode.
		String url = "http://" + controller.getRequest().getServerName() // 服务器地址
				// + ":"
				// + getRequest().getServerPort() //端口号
				+ controller.getRequest().getContextPath() // 项目名称
				+ controller.getRequest().getServletPath();// 请求页面或其他地址
		String qs = controller.getRequest().getQueryString(); // 参数
		if (qs != null) {
			url = url + "?" + (controller.getRequest().getQueryString());
		}
		System.out.println("url>>>>" + url);
		String timestamp = createTimestamp();
		//这里参数的顺序要按照 key 值 ASCII 码升序排序
		//注意这里参数名必须全部小写，且必须有序
		String str = "jsapi_ticket=" + jsapi_ticket +
				"&noncestr=" + nonce_str +
				"&timestamp=" + timestamp +
				"&url=" + url;

		String signature = HashKit.sha1(str);

		System.out.println("appId " + ApiConfigKit.getApiConfig().getAppId()
				+ "  nonceStr " + nonce_str + " timestamp " + timestamp);
		System.out.println("url " + url + " signature " + signature);
		System.out.println("nonceStr " + nonce_str + " timestamp " + timestamp);
		System.out.println(" jsapi_ticket " + jsapi_ticket);
		System.out.println("nonce_str  " + nonce_str);
		
		controller.setAttr("appId", ApiConfigKit.getApiConfig().getAppId());
		controller.setAttr("nonceStr", nonce_str);
		controller.setAttr("timestamp", timestamp);
		controller.setAttr("url", url);
		controller.setAttr("signature", signature);
		controller.setAttr("jsapi_ticket", jsapi_ticket);
		
		ApiConfigKit.removeThreadLocalApiConfig();
	}

	private static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	private static String createNonceStr() {
		return UUID.randomUUID().toString();
	}

}
