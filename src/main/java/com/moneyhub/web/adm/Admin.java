package com.moneyhub.web.adm;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Admin{
	private String aid,
	pwd,
	empno,
	ename,
	job,
	mgr,
	hiredate,
	sal,
	comm,
	deptno,
	dname,
	loc;
}
