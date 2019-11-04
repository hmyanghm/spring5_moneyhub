package com.moneyhub.web.brd;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.ISupplier;
import com.moneyhub.web.utl.Printer;

@RestController
@RequestMapping("/articles") //외부에 노출되는 url이기 때문에 약자 대신 제대로 된 이름 쓰는게 좋음
public class ArticleCtrl {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCtrl.class);
	@Autowired Map<String, Object>map;
	@Autowired Article art;
	@Autowired Printer printer;
	@Autowired ArticleMapper articleMapper;
	@Autowired List<Article>list;
	
	@PostMapping("/")
	public Map<?,?> write(@RequestBody Article param){
		printer.accept("글쓰기 들어옴");
		param.setBoardType("게시판");
		printer.accept(param.toString());
		IConsumer<Article> c = t-> articleMapper.insertArticle(param); 
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		ISupplier<String> s = () -> articleMapper.countArticle();
		printer.accept("카운팅: "+s.get());
		map.put("count",s.get());
		printer.accept("글쓰기 나감"+map.get("msg"));
		return map;
	}
	
	@GetMapping("/page/{pageNo}")
	public Map<?,?> list(@PathVariable String pageNo){
		System.out.println("넘어온 페이지 넘버: "+pageNo);
		list.clear();
		ISupplier<List<Article>> s =()-> articleMapper.selectAll();
		printer.accept("해당 페이지 글 목록 \n"+s.get());
		map.clear();
		map.put("articles", s.get());
		map.put("pages", Arrays.asList(1,2,3,4,5));
		return map;
	}
	
	@GetMapping("/count")
	public Map<?,?> count(){
		ISupplier<String> s = () -> articleMapper.countArticle();
		printer.accept("카운팅: "+s.get());
		map.clear();
		map.put("count",s.get());
		return map;
	}
	
	@GetMapping("/{artseq}")
	public Map<?,?> read(@PathVariable String artseq, @RequestBody Article param){
		return null;
	}
	
	@PutMapping("/{artseq}")
	public Map<?,?> update(@PathVariable String artseq, @RequestBody Article param){
		return null;
	}
	
	@DeleteMapping("/{artseq}")
	public Map<?,?> delete(@PathVariable String artseq, @RequestBody Article param){
		return null;
	}
	

}
