package com.wx.weixin.services;

public class StaticMsgService {
	
	private static final String HELP_STR = "测试公众号被动回复消息：\n1 - 文本消息\n2 - 图片消息\n3 - 语音消息\n4 - 视频消息\n5 - 音乐消息\n6 - 单图文消息\n7 - 多图文消息\n\n请输入编号 -- Waiting...";
	
	private static final String HOLDER_STR = "尚未完成，敬请期待";
	
	public static String getHelpStr(String prefix) {
		return prefix + HELP_STR;
	}
	
	public static String getHolderStr() {
		return HOLDER_STR;
	}
	
}
