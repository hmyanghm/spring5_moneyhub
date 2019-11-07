package com.moneyhub.web.pxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
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
	private int totalCount, startRow, endRow, 
				pageCount, pageNum, pageSize, startPage, endPage,
				blockCount, blockNum, nextBlock, prevBlock;
	private boolean existPrev, existNext;
	private String search;
	private final int BLOCK_SIZE = 5;
	@Autowired Printer p;
	@Autowired ArticleMapper articleMapper;
	
	@SuppressWarnings("unused")
	public void paging() {
		ISupplier<String> s = ()->articleMapper.countArticle();
		totalCount = Integer.parseInt(s.get());
		pageCount = (totalCount % pageSize != 0) ? (totalCount / pageSize) + 1 : totalCount / pageSize;
		startRow = (pageNum - 1) * pageSize;
		endRow = (pageNum == pageCount) ? totalCount - 1 : startRow + pageSize - 1;
		blockCount = (pageCount % BLOCK_SIZE != 0) ? (pageCount / BLOCK_SIZE) + 1 : (pageCount / BLOCK_SIZE);
		blockNum = (pageNum - 1) / BLOCK_SIZE;
		startPage = (blockNum * BLOCK_SIZE) + 1;
		endPage = ((blockNum + 1) != blockCount) ? startPage + (BLOCK_SIZE - 1) : pageCount;
		existPrev = blockNum != 0;
		existNext = (blockNum + 1) != blockCount;
		nextBlock = startPage + pageSize;
		prevBlock = startPage - pageSize;
		
		System.out.println("프록시 안에서 찍은 전체글 수: "+totalCount);
		System.out.println("프록시 안에서 찍은 pageCount: "+pageCount);
		System.out.println("프록시 안에서 찍은 startRow: "+startRow);
		System.out.println("프록시 안에서 찍은 endRow: "+endRow);
		System.out.println("프록시 안에서 찍은 blockCount: "+blockCount);
		System.out.println("프록시 안에서 찍은 blockNum: "+blockNum);
		System.out.println("프록시 안에서 찍은 startPage: "+startPage);
		System.out.println("프록시 안에서 찍은 endPage: "+endPage);
		System.out.println("프록시 안에서 찍은 existPrev: "+existPrev);
		System.out.println("프록시 안에서 찍은 existNext: "+existNext);
		System.out.println("프록시 안에서 찍은 nextBlock: "+nextBlock);
		System.out.println("프록시 안에서 찍은 prevBlock: "+prevBlock);
		
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
	
/*	public int random(int param){
		Supplier<Integer> s = () -> (int)(Math.random()*100)+1;
		return s.get();
	}*/
	
	public int random(int a, int b) {
		BiFunction<Integer, Integer, Integer> f = (t,u)->(int)(Math.random()*(u-t))+t;
		return f.apply(a, b);
	}
}
