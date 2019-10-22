package com.moneyhub.web.usr;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.utl.Printer;

@RestController
@RequestMapping("/user")

public class UserCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	@Autowired Map<String, Object>map;
	@Autowired User client;
	@Autowired Printer printer;
	
	@PostMapping("/")
	public User join(@RequestBody User param) {
		//logger.info("AJAX가 보낸 아이디와 비번{} ",param.getCid()+","+param.getPwd());
		printer.accept("람다 프린터가 출력한 값"+param.getCid()+","+param.getPwd());
		client.setCid(param.getCid());
		client.setPwd(param.getPwd());
		client.setCname(param.getCname());
		logger.info("map에 담긴 아이디와 비번{} ",client.toString());
		return client;
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User param){
		logger.info("AJAX가 보낸 아이디와 비번{} ",param.getCid()+","+param.getPwd());
		client.setCid(param.getCid());
		client.setPwd(param.getPwd());
		logger.info("client에 담긴 아이디와 비번{} ",client.getCid()+","+client.getPwd());
		return client;
	}
}
