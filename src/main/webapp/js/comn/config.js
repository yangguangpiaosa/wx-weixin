seajs.config({
	debug: true,
	charset: 'utf-8',
	base: cxt,
	alias: {
		'jquery': 'js/comn/jquery-sea',
		'wx': 'http://res.wx.qq.com/open/js/jweixin-1.0.0'
	},
	paths: {
		
	},
	preload: ['jquery'],
	map: [[/^(.*\.(?:css|js))(.*)$/i, '$1?v='+v]]  //map,批量更新时间戳
});
