"use strict";
var auth = auth || {};
auth = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.auth';	
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
	
	let onCreate = () =>{
        init()
        $.when(
        	$.getScript(vue),
        	$.getScript(router_js),
        	$.getScript(brd_js),
        	$.getScript(cookie_js)
        )
        .done(()=>{
        	setContentView()
    		$('#a_go_join').click(e=>{
         		e.preventDefault()
         		$('head').html(auth_vue.join_head())
		        $('body').addClass('text-center')
		        .html(auth_vue.join_body())
		        $('#clientid').keyup(()=>{
		        	if($('#clientid').val().length > 2)
		        		if(!existId($('#clientid').val())){}
		        })		        
		        $('<button>',{
		            text : 'Continue to checkout',
		            href : '#',
		            click : e=>{            	
		            	e.preventDefault(); 
		            		join()            	
		            }
		        })
		        .addClass('btn btn-primary btn-lg btn-block')
		        .appendTo('#btn_join')         		
    		})
        }).fail(()=>{alert(WHEN_ERR)})
    }
	
	let setContentView =()=>{
		$('head').html(auth_vue.login_head({css: $.css(), img: $.img()}))
        $('body').addClass('text-center')
        .html(auth_vue.login_body({css: $.css(), img: $.img()}))
		login()
		access()
    }
	
	let join =()=>{
		init()
    	let data = {cid : $('#clientid').val(),
					pwd : $('#password').val(),
					cname : $('#cname').val()}
//            	alert('전송아이디: '+data.cid)
                $.ajax({                	
			    	url : _+'/client/',
			    	type : 'POST',
			    	dataType : 'json',
			    	data : JSON.stringify(data),
			    	contentType : 'application/json',
			    	success : d => {
//			    		alert('AJAX 성공 아이디: '+d.msg)
			    		if(d.msg==='SUCCESS'){ 
			    			$('head').html(auth_vue.login_head({css: $.css(), img: $.img(),  js: $.js()}))
			    	        $('body').addClass('text-center')
			    	        .html(auth_vue.login_body({css: $.css(), img: $.img(),  js: $.js()}))
			    			login() 
			    		}
			    		else 
			    			alert('회원가입 실패')
			    	},
			    	error : e => {
			    		alert('AJAX JOIN 실패')
			    	}
            	})
    }
	
	let existId =x=> {
		init()
		$.ajax({
			url : _ + '/client/' + x + '/exist',
//			type : 'GET',//default라 지워도 된다.
			contentType : 'application/json',
			success : d => {
//				alert('exist success')
				if(d.msg==='SUCCESS'){
					$('#dupl_check')
					.val('사용가능한 ID입니다.')
					.css('color', 'blue')
					return true;
				}else{
					$('#dupl_check')
					.val('이미 존재하는 ID입니다.')
					.css('color', 'red')
					return false;
				}
			},
			error : e => {
				alert('AJAX fail')
				return false;
			}
		})
	}
	
	let login =()=>{
        $('<button>',{
        	type : "submit",
        	text : "로그인",
        	click : e => {
        		e.preventDefault()           		
	        	$.ajax({
	        		url : _+'/client/'+$('#cid').val(), 
	        		type : 'POST',
				   	data : JSON.stringify({
				   		cid : $('#cid').val(),
				   		pwd : $('#pwd').val()
				   	}),
				   	dataType : 'json',
				   	contentType : 'application/json',
				   	success : d => {
				   		setCookie("CLIENTID",d.cid)
				   		alert('저장된 쿠키: '+getCookie("CLIENTID"))
				   		brd.onCreate()
				   	},
				   	error: e =>{
				   		alert('AJAX ERROR')
				   	}
	        	})
        	}
        })
        .addClass('btn btn-lg btn-primary btn-block')
    	.appendTo('#btn_login')	
    }
	
	let access =()=>{
		$('#a_go_admin').click(()=>{
		let ok = confirm('사원입니까?')
		if(ok){
			let aid = prompt('사원번호를 입력하시오')
			let pwd = prompt('비밀번호를 입력하시오')
			alert('입력한 사번: '+aid)
			$.ajax({
				url: _+'/admins/'+aid,
				type: 'POST',
				data: JSON.stringify(json),
				dataType: 'json',
				contentType: 'application/json',
				success: d => {
					if(d === 'SUCCESS'){
						alert('환영합니다')
						admin.onCreate()
					}else{
						alert('접근권한이 없습니다')
						app.run(_)
					}
				},
				error: e => {}
			})
		}
	})
	}
	return {onCreate, join, login}
	
})();