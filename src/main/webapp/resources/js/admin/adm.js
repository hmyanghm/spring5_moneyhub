"use strict"
var adm = adm || {}
adm = (()=>{
	let _, js, css, img, vue, brdvue, brd_js, router_js, cookie_js;
	let init = () => {
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		vue = js + '/vue/auth_vue.js'	//스트링값, 객체가 아니다
		brdvue = js + '/vue/brd_vue.js'
		brd_js = js + '/brd/brd.js'
		router_js = js +'/cmm/router.js'
		cookie_js = js + '/cmm/cookie.js'
	}	
	let onCreate =()=>{
		init()
		setContetView()
	}
	let setContentVeiw =()=>{}
	return {onCreate}
})();