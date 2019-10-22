package com.moneyhub.web.adm;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data @Component
public class Admin implements Serializable{
	private static final long serialVersionUID = 1L;
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
