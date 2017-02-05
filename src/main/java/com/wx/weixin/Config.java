package com.wx.weixin;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.render.ViewType;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.wx.weixin.api.base.WeixinApiController;
import com.wx.weixin.api.base.WeixinController;
import com.wx.weixin.api.base.WeixinMsgController;
import com.wx.weixin.api.oauth2.Oauth2;
import com.wx.weixin.utils.StaticVersionHandler;
import com.wx.weixin.web.controller.IndexController;

public class Config extends JFinalConfig {
	
	private static final Log log = Log.getLog(JFinalConfig.class);

	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setEncoding("utf-8");
		me.setViewType(ViewType.JSP);
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", WeixinController.class);
		me.add("/msg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class);
		me.add("/oauth2", Oauth2.class);
		me.add("/web/index", IndexController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("cxt_path"));
		me.add(new StaticVersionHandler("v"));
	}
	
	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
		log.info("================== SYSTEM STARTED ==================");
	}

	@Override
	public void beforeJFinalStop() {
		super.beforeJFinalStop();
		log.info("================== SYSTEM STOPPED ==================");
	}
	
	public static void main(String [] args) {
		//Disable this feature, using tomcat 7.0.47
		//JFinal.start("src/main/webapp", 8088, "/matrix", 5);
	}

}
