package com.jmdev.crazytrip.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmdev.crazytrip.model.Article;
import com.jmdev.crazytrip.service.ArticleService;

@RestController
@RequestMapping(value="/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService as;
	
	@GetMapping("")
	public Iterable<Article> findAll(){
		return this.as.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Article> findById(@PathVariable("id") int id){
		return this.as.findById(id);
	}
	
	@GetMapping("/search")
	public List<Article> findByString(@RequestParam("search") String search){
		return this.as.findByString(search);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> createArticle(@RequestBody Article article) {
	Article savedArticle = this.as.save(article);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedArticle.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateArticle(@RequestBody Article article, @PathVariable("id") int id) {
		Optional<Article> articleOptional = this.as.findById(id);
		
		if (!articleOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		article = this.as.update(article, articleOptional.get());

		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable("id") int id){
		this.as.deleteById(id);
	}
}
