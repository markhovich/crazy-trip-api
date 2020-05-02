package com.jmdev.crazytrip.service;

import java.util.List;
import java.util.Optional;

import com.jmdev.crazytrip.model.Article;
import com.jmdev.crazytrip.model.Comment;
import com.jmdev.crazytrip.model.User;

public interface CommentService {
	public Iterable<Comment> findAll();
	public Optional<Comment> findById(int id);
	public List<Comment> findByString(String search);
	public Comment save(Comment comment, Article art, User user);
	public Comment update(Comment newComment, Comment comment);
	public void deleteById(int id);
}
