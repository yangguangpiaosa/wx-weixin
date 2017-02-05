package com.wx.weixin.api.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.wx.weixin.utils.WeixinUtil;

public class Oauth2 extends ApiController{
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		return WeixinUtil.getApiConfig();
	}
	
	public void index() {
		//用户同意授权，获取code
		String code = getPara("code");
		if (StrKit.isBlank(code)) {
			renderText("code is Blank!");
		}
		String appId  = getApiConfig().getAppId();
		String secret = getApiConfig().getAppSecret();
		SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, secret, code);

		System.out.println("snsAccessToken: " + snsAccessToken);
		String openId = snsAccessToken.getOpenid();
		String token  = snsAccessToken.getAccessToken();

		//拉取用户信息(需scope为 snsapi_userinfo)
		ApiResult apiResult = SnsApi.getUserInfo(token, openId);
		System.out.println("openId: " + openId);

		String nickname = apiResult.get("nickname");
		String sex = apiResult.get("sex").toString();
		String city = apiResult.get("city");
		String province = apiResult.get("province");
		String country = apiResult.get("country");
		String headimgurl = apiResult.get("headimgurl");

		System.out.println("nickname:"+nickname);
		try {
			System.out.println("nickname:"+URLEncoder.encode(nickname, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("sex:" + sex);//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		System.out.println("city:" + city);//城市
		System.out.println("province:" + province);//省份
		System.out.println("country:" + country);//国家
		System.out.println("headimgurl:" + headimgurl);
		
		forwardAction("/web/index");
	}
	
	/**
	 * 获取用户授权
	 */
	public void oauth() {
		String appId = getApiConfig().getAppId();
		String redirectUri = WeixinUtil.urlEncodeUTF8("http://wxdev.iask.in/weixin/oauth2");
		String state = System.currentTimeMillis() + "";
		boolean snsapiBase = getPara("t").trim().equals("y") ? false : true;
		String url = SnsAccessTokenApi.getAuthorizeURL(appId, redirectUri, state, snsapiBase);
		redirect(url);
	}
	
}
