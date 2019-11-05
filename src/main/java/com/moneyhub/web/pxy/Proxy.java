package com.moneyhub.web.pxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.moneyhub.web.brd.ArticleMapper;
import com.moneyhub.web.cmm.ISupplier;
import com.moneyhub.web.utl.Printer;

import lombok.Data;

@Component @Data @Lazy
public class Proxy {
	private int pageNum, pageSize, startRow, endRow;
	private String search;
	private final int BLOCK_SIZE = 5;
	@Autowired Printer p;
	@Autowired ArticleMapper articleMapper;
	
	@SuppressWarnings("unused")
	public void paging() {
		ISupplier<String> s = ()->articleMapper.countArticle();
		int totalCount = Integer.parseInt(s.get());
		int pageCount = (totalCount % pageSize != 0) 
						? (totalCount/pageSize)+1
						: totalCount/pageSize;
		startRow = (pageNum-1)*pageSize;
		endRow = (pageNum==pageCount) 
				? totalCount-1
				: startRow+pageSize-1;
		/*int blockCount = (blockCount != pageSize) 
					? pageCount % pageSize 
					: pageSize;*/
		int blockCount = (pageCount % BLOCK_SIZE != 0)
				?(pageCount/BLOCK_SIZE)+1
				:(pageCount/BLOCK_SIZE);
		/*int blockNum = (totalCount/pageSize)+1;*/
		int blockNum = (pageNum -1) / BLOCK_SIZE;
	/*	int startPage = blockCount * BLOCK_SIZE-4;
		int endPage = BLOCK_SIZE * (blockNum+1);
		boolean existPrev = false;
		boolean existNext = false;*/
	}
	
	public int parseInt(String param) {
		Function<String, Integer> f = s -> Integer.parseInt(s);
		return f.apply(param);
	}
	
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
