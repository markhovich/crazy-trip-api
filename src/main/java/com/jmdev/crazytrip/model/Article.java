package com.jmdev.crazytrip.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="article")
public @Data class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	private String image;
	private Date created;
	private int loveIt;
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments;

	public Article() {}

	public Article(int id) {
		this.id = id;
	}

	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public Article(String title, String content, String image, Date created, int loveIt) {
		this.title = title;
		this.content = content;
		this.image = image;
		this.created = created;
		this.loveIt = loveIt;
	}

	public void addComment(Comment comment) {
		if(this.comments == null) {
			this.comments = new ArrayList<Comment>();
		}
		this.comments.add(comment);
		comment.setArticle(this);
	}

	public void removeComment(Comment comment) {
		if(this.comments == null) {
			this.comments = new ArrayList<Comment>();
		}
		this.comments.remove(comment);
		comment.setArticle(null);
	}
}
