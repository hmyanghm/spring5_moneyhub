package com.moneyhub.web.brd;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface ArticleMapper {
	public void insertArticle(Article client);
	public String countArticle();
	public List<Article> selectAll();
}
