package com.jmdev.crazytrip.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmdev.crazytrip.dao.UserRepository;
import com.jmdev.crazytrip.model.Article;
import com.jmdev.crazytrip.model.User;

@Service
public class IUserService implements UserService{

	@Autowired
	private UserRepository ur;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Iterable<User> findAll() {
		return this.ur.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return this.ur.findById(id);
	}

	@Override
	public User save(User urer) {
		urer.setCreated(new Date());
		urer.setRole(0);
		System.out.println(urer);

		return this.ur.save(urer);
	}
	
	@Override
	public User update(User newUser, User urer) {
		urer.setName(newUser.getName());
		urer.setEmail(newUser.getEmail());
		urer.setPassword(newUser.getPassword());

		return this.ur.save(urer);
	}
	
	@Override
	public void deleteById(int id) {
		this.ur.deleteById(id);
	}

	@Override
	public List<User> findByString(String search) {
		if(search.length() == 0) {
			return null;
		}
		
		return this.em.createQuery("FROM User WHERE name "
				+ "LIKE '%" + search + "%' OR email LIKE '%" + search + "%'").getResultList();
	}

}
