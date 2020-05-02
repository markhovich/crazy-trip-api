package com.jmdev.crazytrip.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="user")
public @Data class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private Date created;
	private int role;
	@OneToMany(mappedBy="user", cascade= {CascadeType.ALL})
	@JsonIgnore
	private List<Comment> comments;

	public User() {}

	public User(int id) {
		this.id = id;
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password, Date created, int role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.role = role;
	}

	public void addComment(Comment comment) {
		if(this.comments == null) {
			this.comments = new ArrayList<Comment>();
		}
		this.comments.add(comment);
		comment.setUser(this);
	}
}
