"use strict"
var navi = navi || {}
navi = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.navi'
	let _, js, css, img, brd_vue_js, router_js, brd_js, cookie_js, auth_js
	let init =()=> {
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		brd_vue_js = js + '/vue/brd_vue.js'
		router_js = js + '/cmm/router.js'
		brd_js = js + '/brd/brd.js'
		cookie_js = js + '/cmm/cookie.js'
		auth_js = js + '/cmm/auth.js'
	}
	let onCreate=()=>{
		init()
		$.when(
			$.getScript(auth_js),
			$.getScript(brd_js)
		).done(()=>{
			setContentView()
		}).fail(()=>{alert(WHEN_ERR)})
	}
	let setContentView=()=>{
		$('<a>',{
			href : '#',
			text : '글쓰기'
		})
		.addClass('nav-link')
		.appendTo('#menu_write')
		.click(e=>{	
			e.preventDefault()
			brd.write()

		})
		$('<a>',{
			href : '#',
			text : '로그아웃'
		})
		.addClass('nav-link')
		.appendTo('#menu_logout')
		.click(e=>{	
			e.preventDefault()
			alert('로그아웃 클릭 시')
			deleteCookie()
			app.run(_)
		})
	}
	return {onCreate}
})();