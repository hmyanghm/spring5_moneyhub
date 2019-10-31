"use strict"
var adm = adm || {}
adm = (()=>{
	let _, js, css, img, navi_vue_js;
	let init =()=>{
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		navi_vue_js = js + '/vue/navi_vue.js'
	}	
	let onCreate =()=>{
		alert('환영합니다')
		init()
		$.when(
			$.getScript(navi_vue_js)
		).done(()=>{
			setContentView()
		}).fail(()=>{
			alert(WHEN_ERR)
		})
	}
	let setContentView =()=>{
		$('body').empty()
		$(navi_vue.nav())
		.appendTo('body')
		$('<table id="tab"><tr></tr></table>')
		.css({border: '1px solid black', width: '80%', height: '90%', 'margin': '0 auto'})
		.appendTo('body')
		$.each(
			[{id:'left',width:'15%'},
			{id:'right',width:'85%'}],
			(i,j)=>{
			$('<td id="'+j.id+'"></td>')
			.css({border: '2px solid #7792af', width: j.width, 'vertical-align': 'top'})
			.appendTo('#tab tr')
		})
		$.each(
			[{txt: '웹크롤링', name: 'web_crawl'},
			{txt: '고객관리', name: 'cust_mgmt'},
			{txt: '상품등록', name: 'item_reg'},
			{txt: '상품조회', name: 'item_srch'},
			{txt: '상품수정', name: 'item_mod'},
			{txt: '상품삭제', name: 'item_del'}],
			(i,j)=>{
			$('<div name="'+j.name+'">'+j.txt+'</div>')
			.css({border: '2px solid #7792af', margin: '0 auto', height: '7%', 'line-height': '50px'})
			.appendTo('#left')
			.hover(function(){
				$(this).addClass('active')
				$(this).siblings().removeClass('active')
				switch($(this).attr('name')){
				case 'web_crawl':
					webCrawl()
					break;
				case 'cust_mgmt':
					custManager()
					break;
				case 'item_reg':
					
					break;
				case 'item_srch':
					
					break;
				case 'item_mod':
					
					break;
				case 'item_del':
					
					break;
				}
			})
		})
	let webCrawl=()=>{
		$('<form id="formlist"><select name="list"></select></form>').appendTo('#right')
		$.each([{name: '네이버', url: 'http://www.naver.com/'},
				{name: '구글', url: 'http://www.google.co.kr/'},
				{name: '다음', url: 'http://www.daum.net/'},
				{name: '네이트', url: 'http://www.nate.com/'}],
				(i,j)=>{
					$('<option value="'+j.url+'">'+j.name+'</option>')
					.appendTo('#formlist select')
				})
				$('<input type="submit">',{
				}).appendTo('#right')
	
	let custManager=()=>{
			alert('고객관리')
		}
/*				<h2>Allow Multiple Seletcions</h2>
				<p>Use the multiple attribute to allow the user to select more than one value.</p>

				<form action="/action_page.php">
				  <select name="cars" size="4" multiple>
				    <option value="volvo">Volvo</option>
				    <option value="saab">Saab</option>
				    <option value="fiat">Fiat</option>
				    <option value="audi">Audi</option>
				  </select>
				  <br><br>
				  <input type="submit">
				</form>

				<p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p>*/
    }
		
		
		
		/*.append('<div>'+arr[0]+'</div>')
		.append('<div></div>')
		.append('<div></div>')
		.append('<div></div>')
		.append('<div></div>')*/
		/*$('#custManager')
		.click(()=>{
			custManager()
		})
		*/
		/*$('<table id="customers">'+
		'  <tr>'+
		'    <th>Company</th>'+
		'    <th>Contact</th>'+
		'    <th>Country</th>'+
		'  </tr>'+
		'</table>')*/
	}
	return {onCreate}
})();