package com.jmdev.crazytrip.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="comment")
public @Data class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;
	private Date created;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="article_id")
	private Article article;

	public Comment() {}

	public Comment(String content) {
		this.content = content;
	}

	public Comment(String content, Article article, User user) {
		this.content = content;
		this.article = article;
		this.user = user;
	}
	
	@JsonIgnore
	public Article getArticle() {
		return this.article;
	}
	
	@JsonProperty
	public void setArticle(Article article) {
		this.article = article;
	}
}
