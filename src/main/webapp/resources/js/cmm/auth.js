"use strict";
var auth = auth || {};
auth =(()=>{
		const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.'
   let _, js, vue;
   let init =()=> {
       _ = $.ctx();
       js = $.js();
       vue = js + '/vue/auth_vue.js'
   }
   let onCreate =()=> {
       init();
       $.getScript(vue).done(()=>{
    	  setContentView()
    	  $('#a_go_join').click(e=>{
    		  e.preventDefault()
    		  join()
    	  })
      }).fail(()=>{alert(WHEN_ERR)})
   }
   let setContentView =()=>{
	   login()
   }
   let join =()=>{
	   $('head').html(auth_vue.join_head())
	   $('body').html(auth_vue.join_body())
	   $('<button>',{
		   text: 'Continue to checkout',
		   href: '#',
		   click : e=>{
			   e.preventDefault();
               let data = {cid : $('#clientid').val(), pwd : $('#password').val(), cname : $('#cname').val()}
               alert('전송아이디: '+data.cid)
               $.ajax({
            	   url : _+'/client/',
            	   type : 'POST',
            	   dataType : 'json',
            	   data : JSON.stringify(data),
            	   contentType : 'application/json',
            	   success : d => {
            		   alert('AJAX 성공 아이디: '+d.msg)
            		   if(d.msg === 'SUCCESS')
            			   login()
            		   else
            			   alert('회원가입 실패')
            	   },
            	   error : e => {
            		   alert('AJAX 실패');
            	   }
               })
		   }
	   })
   		.addClass("btn btn-lg btn-primary btn-block")
   		.appendTo('#btn_join')
   }
   let login =()=>{
	   let x = {css: $.css(), img: $.img()}
	   $('head').html(auth_vue.login_head(x))
	   $('body').addClass('text-center')
	   			.html(auth_vue.login_body(x))
       $('<button>',{
				type : "submit",
				text : "sign in",
				click : e => {
					e.preventDefault()
					let data = {cid : $('#cid').val(), pwd : $('#pwd').val()}
					$.ajax({
						url : _+'/client/',
						type : 'POST',
						dataType : 'json',
						data : JSON.stringify(data),
						contentType : 'application/json',
						success : d => {
							alert(d.cname+'님 환영합니다.')
							mypage()
						},
						error : e => {
							alert('AJAX 실패')
						}
					})
				}
        })
   		.addClass("btn btn-lg btn-primary btn-block")
   		.appendTo('#btn_login')
   }
   let mypage =()=>{
	   let x = {css : $.css(), img : $.img()}
	   $('body').html(auth_vue.mypage(x))
   }
   return{onCreate, join, login}
})();