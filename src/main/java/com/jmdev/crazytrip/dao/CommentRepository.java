package com.jmdev.crazytrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmdev.crazytrip.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
