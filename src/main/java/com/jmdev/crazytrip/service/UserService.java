package com.jmdev.crazytrip.service;

import java.util.List;
import java.util.Optional;

import com.jmdev.crazytrip.model.Article;
import com.jmdev.crazytrip.model.User;

public interface UserService {
	public Iterable<User> findAll();
	public Optional<User> findById(int id);
	public User findByName(String name);
	public User findByEmail(String email);
	public User save(User user);
	public User update(User newUser, User user);
	public void deleteById(int id);
	List<User> findByString(String search);
}
