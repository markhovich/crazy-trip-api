package com.jmdev.crazytrip.service;

import java.util.List;
import java.util.Optional;

import com.jmdev.crazytrip.model.Article;

public interface ArticleService {
	public Iterable<Article> findAll();
	public Optional<Article> findById(int id);
	public List<Article> findByString(String search);
	public Article save(Article article);
	public Article update(Article newArticle, Article article);
	public void deleteById(int id);
}
