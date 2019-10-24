"use strict";
var auth = auth || {}

auth = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.'
	let _,js,vue;
	let init = ()=>{
		_=$.ctx();
		js=$.js();
		vue = js+'/vue/auth_vue.js';
	} 
	let onCreate =()=>{
		init();
		$.getScript(vue).done(()=>{
        	setContentView()
    		$('#a_go_join').click(e=>{
         		e.preventDefault()
  				$.getScript(vue)
					$('head').html(auth_vue.join_head())
					$('body').html(auth_vue.join_body())
					$('<button>',{
							text : '회원가입' , 
							href: '#' ,
							click : e=>{
				         		e.preventDefault()
								let data = { 
										cid :  $('#clientid').val() ,
										pwd : $('#password').val(),
										cname : $('#cname').val()
								}
				         		existId(data);
				         		
							} 
						})
						.addClass('btn btn-primary btn-lg btn-block')
						.appendTo('#btn_join')       		
    		})
        }).fail(()=>{alert(WHEN_ERR)})
	}
	let setContentView = ()=>{
		login()
	}
	let join = data=>{
				$.ajax({
					url : _+'/client/', 
					type : 'POST',
					dataType : 'json',
					data: JSON.stringify(data) , 
					contentType : 'application/json',
					success : d =>{
						alert('AJAX 성공 ' + d.msg)
						if (d.msg==='SUCCESS') 
							login()
					},
					error : e =>{
						alert('AJAX실패')
					}
				})    
	}	
	let existId = x =>{
		$.ajax({
			url : _+'/client/'+x.cid+'/exist', 
			type : 'GET',
			contentType : 'application/json',
			success : d =>{
				if (d.msg==='SUCCESS') {
					alert('사용 가능한 아이디입니다 ' + d.msg);
					join(x)
					return true;
				}else{
					alert('중복된 아이디 입니다.');	
				return false;
				}
			},
			error : e =>{
				alert('exist error')
				return false;
			}
		})    
	}
	let login = ()=>{
		let x = {css : $.css(), img : $.img(), js:$.js() }
		$('head').html(auth_vue.login_head(x))
		$('body')
		.addClass('text-center')
		.html(auth_vue.login_body(x))
		$('<button>',{
			type: "submit",
			text : "Log In",
			click: 	e=>{									
					e.preventDefault()
					let data = { cid : $('#clientid').val(),
								 pwd : $('#password').val()}
					$.ajax({
						url : _+'/client/'+data.cid, 
						type : 'POST',
						dataType : 'json',
						data: JSON.stringify(data) , 
						contentType : 'application/json',
						success : d =>{
								alert(d.cname + '님 환영합니다.')
								board(d)
						},
						error : e =>{
							alert('AJAX ERROR' )
						}
					})    
			}
		})
		.addClass('btn btn-lg btn-primary btn-block')
		.appendTo('#btn_login')		
	}
	let board = d =>{
		let x = {css : $.css(), img : $.img(), js:$.js(), resultData: d}
			$('head').html(auth_vue.brd_head(x))
			$('body').html(auth_vue.brd_body(x))
	}
	return {onCreate, join, login, board}
})();
