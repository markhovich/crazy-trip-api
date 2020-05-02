package com.jmdev.crazytrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmdev.crazytrip.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
