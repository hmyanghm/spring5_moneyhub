var brd = brd || {};
brd = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.';	
	let _, js,  brdvue, name, $clietid;
	let init = () => {
		_ = $.ctx();
		js = $.js();
		brdvue = js + '/vue/brd_vue.js'
		$clientid = $.clientid()
	}
	
	let onCreate = () =>{
		init()
		$.getScript(brdvue).done(()=>{
			setContentView()
			navigation()
		}).fail(()=>{alert(WHEN_ERR)})	
	}
	let setContentView =()=>{
			$('head').html(brd_vue.brd_head({css: $.css(), img: $.img(), js: $.js()}))
			$('body').addClass('text-center')
			.html(brd_vue.brd_body({css: $.css(), img: $.img(), js: $.js()}))  	
			$('#recentid .media').remove()//.media유튜브 건들때 씀
			$('#recentid .d-block').remove()
			$('#recentid').append('<h1>등록된 글이 없습니다</h1>')
//			$('#recentid').text('등록된 글이 없습니다')
	}
	
	let write = () =>{
//		alert('>>> '+cid)
		alert('글쓰기 클릭')
		$('#recent_updates').html(brd_vue.brd_write())
		alert('사용자 아이디' + $clientid)
		$('#writer').val($clientid)
		$('#suggestions').remove()
		$('<input>',{
			style: "float:right;width:100px;margin-right:10px",
			value: "취소"
		})
		.addClass("btn btn-danger")
		.appendTo('#write_form')
		.click(()=>{
			
		})
		$('<input>',{
			type: "submit",
			style: "float:right;width:100px;margin-right:10px",
			value: "전송"
		})
		.addClass("btn btn-primary")
		.appendTo('#write_form')
		.click(e=>{
			e.preventDefault()
			let json = {
					cid : $('#writer').val(),
					title : $('#title').val(),
					content :$('#content').val()
					
			}
			alert('글 내용: ' +json.content)
			$.ajax({
				url : _+'/articles/',
				type : 'POST',
				data : JSON.stringify(json),
				dataType : 'json',
				contentType : 'application/json',
				success : d=>{
					$('#recent_updates').html('<h1>목록 불러오기</h1>')
				},
				error : e=>{alert('에러')}
			})
		})
	}
	let navigation =()=>{
		$('<a>',{
			href : '#',
			click : e=>{	
				e.preventDefault()
				write()
			},
			text : '글쓰기',
		})
		.addClass('nav-link')
		.appendTo('#go_write')
	}
	return {onCreate}
})();