package com.moneyhub.web.usr;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Client{
	private String cid,
	pwd,
	cname,
	cstate,
	level,
	hub_account,
	reg,
	wdd;
	
}
