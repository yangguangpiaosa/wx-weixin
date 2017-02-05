<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<script type="text/javascript">
	var cxt = "${cxt_path}";
	var v = "${v}";
	var appId = "${appId}";
	var timestamp = "${timestamp}";
	var nonceStr = "${nonceStr}";
	var signature = "${signature}";
</script>
<script type="text/javascript" src="${cxt_path}/js/comn/sea.js"></script>
<script type="text/javascript" src="${cxt_path }/js/comn/config.js"></script>
<script type="text/javascript">
	<!-- 引入 WeUI -->
	seajs.use('css/weui.min.css');
	seajs.use('js/index/index');
</script>

</head>
<body>

<div class="weui-cells__title">用户登录</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">邮箱</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" placeholder="请输入邮箱"/>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" placeholder="请输入密码"/>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" placeholder="请确认密码"/>
        </div>
    </div>
</div>
<div class="weui-cells__tips">底部说明文字底部说明文字</div>

<div class="weui-cells__title">表单报错</div>

<label for="weuiAgree" class="weui-agree">
    <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox">
    <span class="weui-agree__text">
        	阅读并同意<a href="javascript:void(0);">《相关条款》</a>
    </span>
</label>

<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">登录</a>
    <a class="weui-btn weui-btn_default" href="javascript:" id="showTooltips">注册</a>
</div>

</body>
</html>
