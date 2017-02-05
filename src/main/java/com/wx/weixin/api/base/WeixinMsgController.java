package com.wx.weixin.api.base;

import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.wx.weixin.services.StaticMsgService;
import com.wx.weixin.utils.WeixinUtil;

/**
 * 将此 DemoController 在YourJFinalConfig 中注册路由， 并设置好weixin开发者中心的 URL 与 token ，使
 * URL 指向该 DemoController 继承自父类 WeixinController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WeixinMsgController extends MsgControllerAdapter {
	static Log logger = Log.getLog(WeixinMsgController.class);

	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可 可以通过在请求 url 中挂参数来动态从数据库中获取
	 * ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		return WeixinUtil.getApiConfig();
	}

	/**
	 * 实现父类抽方法，处理文本消息 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin
	 * sdk的基本功能： 本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
	 * 其它类型的消息会在随后的方法中进行测试
	 */
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		if (msgContent.equals("1")) {
			msgContent = "U SB";
			renderOutTextMsg(msgContent);
		} else if (msgContent.equals("2")) {
			/*OutImageMsg imgMsg = new OutImageMsg(inTextMsg);
			imgMsg.setMediaId("1");
			render(imgMsg);*/
			msgContent = StaticMsgService.getHolderStr();
			renderOutTextMsg(msgContent);
		} else if (msgContent.equals("3")) {
			msgContent = StaticMsgService.getHolderStr();
			renderOutTextMsg(msgContent);
		} else if (msgContent.equals("4")) {
			msgContent = StaticMsgService.getHolderStr();
			renderOutTextMsg(msgContent);
		} else if (msgContent.equals("5")) {
			msgContent = StaticMsgService.getHolderStr();
			renderOutTextMsg(msgContent);
		} else if (msgContent.equals("6")) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("测试标题", "厉害了word哥",
					"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",
					"http://www.baidu.com");
			render(outMsg);
		} else if (msgContent.equals("7")) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("测试标题", "厉害了word哥",
					"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png",
					"http://wxdev.iask.in/weixin/");
			outMsg.addNews("JFinal 2.0 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架",
					"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png",
					"http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211063163&idx=1&sn=87d54e2992237a3f791f08b5cdab7990#rd");
			outMsg.addNews("JFinal 1.8 发布,JAVA 极速 WEB+ORM 框架", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^",
					"http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0",
					"http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200313981&idx=1&sn=3bc5547ba4beae12a3e8762ababc8175#rd");
			outMsg.addNews("JFinal 1.6 发布,JAVA 极速 WEB+ORM 框架", "JFinal 1.6 主要升级了 ActiveRecord 插件，本次升级全面支持多数源、多方言、多缓",
					"http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0",
					"http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200121522&idx=1&sn=ee24f352e299b2859673b26ffa4a81f6#rd");
			render(outMsg);
		} else {
			renderOutTextMsg(StaticMsgService.getHelpStr("指令未被识别，请重新输入！\n\n"));
		}
	}

	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
		outMsg.setContent(StaticMsgService.getHelpStr("感谢关注微尘！\n关注微尘，关注攻城狮！ :) \n\n"));
		// 如果为取消关注事件，将无法接收到传回的信息
		render(outMsg);
	}

	@Override
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		String event = inMenuEvent.getEvent();
		String eventKey = inMenuEvent.getEventKey();
		if(event.equals(InMenuEvent.EVENT_INMENU_CLICK)) {
			if (eventKey.equals("text_1")) {
				OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
				outMsg.setContent(StaticMsgService.getHelpStr("感谢关注微尘！\n关注微尘，关注攻城狮！ :) \n\n"));
				render(outMsg);
			} else if (eventKey.equals("news_1")) {
				OutNewsMsg outMsg = new OutNewsMsg(inMenuEvent);
				outMsg.addNews("测试标题", "厉害了word哥",
						"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png",
						"http://wxdev.iask.in/weixin/");
				outMsg.addNews("JFinal 2.0 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架",
						"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png",
						"http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211063163&idx=1&sn=87d54e2992237a3f791f08b5cdab7990#rd");
				outMsg.addNews("JFinal 1.8 发布,JAVA 极速 WEB+ORM 框架", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^",
						"http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0",
						"http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200313981&idx=1&sn=3bc5547ba4beae12a3e8762ababc8175#rd");
				outMsg.addNews("JFinal 1.6 发布,JAVA 极速 WEB+ORM 框架", "JFinal 1.6 主要升级了 ActiveRecord 插件，本次升级全面支持多数源、多方言、多缓",
						"http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0",
						"http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200121522&idx=1&sn=ee24f352e299b2859673b26ffa4a81f6#rd");
				render(outMsg);
			} else if (eventKey.equals("about_me")) {
				OutNewsMsg outMsg = new OutNewsMsg(inMenuEvent);
				outMsg.addNews("测试标题", "厉害了word哥",
						"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",
						"http://www.baidu.com");
				render(outMsg);
			} else {
				
			}
		}
	}

}
