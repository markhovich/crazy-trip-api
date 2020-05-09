package com.jmdev.crazytrip.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jmdev.crazytrip.dao.ArticleRepository;
import com.jmdev.crazytrip.model.Article;

@Service
public class IArticleService implements ArticleService{

	@Autowired
	private ArticleRepository ar;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Iterable<Article> findAll() {
		return this.ar.findAllByOrderByIdDesc();
	}

	@Override
	public Optional<Article> findById(int id) {
		return this.ar.findById(id);
	}

	@Override
	public Article save(Article article) {
		article.setCreated(new Date());
		article.setLoveIt(0);
		return this.ar.save(article);
	}


	@Override
	public Article update(Article newArticle, Article article) {
		newArticle.setId(article.getId());
		newArticle.setComments(article.getComments());
		newArticle.setCreated(article.getCreated());
		return this.ar.save(newArticle);
	}

	@Override
	public void deleteById(int id) {
		this.ar.deleteById(id);

	}

	@Override
	public List<Article> findByString(String search) {
		if(search.length() == 0) {
			return null;
		}
		
		return this.em.createQuery("FROM Article WHERE title "
				+ "LIKE '%" + search + "%' OR content LIKE '%" + search + "%'").getResultList();
	}
}
