package com.jmdev.crazytrip.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmdev.crazytrip.dao.CommentRepository;
import com.jmdev.crazytrip.model.Article;
import com.jmdev.crazytrip.model.Comment;
import com.jmdev.crazytrip.model.User;

@Service
public class ICommentService implements CommentService{

	@Autowired
	private CommentRepository cr;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Iterable<Comment> findAll() {
		return this.cr.findAll();
	}

	@Override
	public Optional<Comment> findById(int id) {
		return this.cr.findById(id);
	}

	@Override
	public Comment save(Comment comment, Article art, User user) {
		comment.setCreated(new Date());
		comment.setArticle(art);
		comment.setUser(user);
		return this.cr.save(comment);
	}

	@Override
	public Comment update(Comment newComment, Comment comment) {
		comment.setContent(newComment.getContent());
		return this.cr.save(comment);
	}
	
	@Override
	public void deleteById(int id) {
		this.cr.deleteById(id);
	}

	@Override
	public List<Comment> findByString(String search) {
		if(search.length() == 0) {
			return null;
		}
		
		return this.em.createQuery("FROM Comment WHERE content LIKE '%" + search + "%'").getResultList();
	}
}
