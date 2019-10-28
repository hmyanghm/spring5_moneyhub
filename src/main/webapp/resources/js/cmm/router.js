"use strict";
function Session(x){
   sessionStorage.setItem('ctx',x);
   sessionStorage.setItem('js',x+'/resources/js');
   sessionStorage.setItem('css',x+'/resources/css');
   sessionStorage.setItem('img',x+'/resources/img');
   return{
       ctx : ()=>{return sessionStorage.getItem('ctx');},
       js : ()=>{return sessionStorage.getItem('js');},
       css : ()=>{return sessionStorage.getItem('css');},
       img : ()=>{return sessionStorage.getItem('img');}
   }
}
function Client(d){
	sessionStorage.setItem('cid', d.cid)
	sessionStorage.setItem('pwd', d.pwd)
	sessionStorage.setItem('cname', d.cname)
	return{
		clientid : ()=>{return sessionStorage.getItem('cid');},
		password : ()=>{return sessionStorage.getItem('pwd');},
		cname : ()=>{return sessionStorage.getItem('cname');}
	}
}