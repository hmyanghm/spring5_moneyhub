package com.moneyhub.web.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.moneyhub.web.utl.Printer;

import lombok.Data;

@Data @Component @Lazy //얘네가 3대장임
public class Proxy {
	private int pageNum;
	private String search;
	@Autowired Printer p;
	//@Autowired List<String> proxyList; //무조건 해야하는 것 1번째
	
	public List<?> crawl(Map<?,?> paramMap){
		String url = "http://"+paramMap.get("site")+"/";
		p.accept("넘어온 URL \n"+url);
		List<String> proxyList = new ArrayList<>();
		proxyList.clear(); //무조건 해야하는 것 2번째
		try {
			Connection.Response response = Jsoup.connect(url)
												.method(Connection.Method.GET)
												.execute();
			Document document = response.parse();
			//String text = document.html();
			String text = document.text();
			p.accept("크롤링한 텍스트 \n"+text);
			proxyList.add(text); //무조건 해야하는 것 3번째
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		return proxyList;
	}
	
}