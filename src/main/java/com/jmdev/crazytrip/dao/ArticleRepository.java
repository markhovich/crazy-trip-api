package com.jmdev.crazytrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmdev.crazytrip.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}
