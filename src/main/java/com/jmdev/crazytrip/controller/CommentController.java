package com.jmdev.crazytrip.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.jmdev.crazytrip.model.*;
import com.jmdev.crazytrip.service.ArticleService;
import com.jmdev.crazytrip.service.CommentService;
import com.jmdev.crazytrip.service.UserService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value="/comments")
@CrossOrigin(origins="http://localhost:4200")
public class CommentController {

	@Autowired
	private CommentService cs;
	@Autowired
	private ArticleService as;
	@Autowired
	private UserService us;

	@GetMapping("")
	public Iterable<Comment> findAll(){
		return this.cs.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Comment> findById(@PathVariable("id") int id){
		return this.cs.findById(id);
	}

	@GetMapping(value="/search")
	public List<Comment> findByString(@RequestParam("search") String search){
		return this.cs.findByString(search);
	}

	@PostMapping("")
	public ResponseEntity<Object> createComment(@RequestBody Comment comment) throws NotFoundException {
		System.out.println(comment);
		Optional<Article> article = this.as.findById(comment.getArticle().getId());
		Optional<User> user = this.us.findById(comment.getUser().getId());

		if (!article.isPresent() || !user.isPresent())
			throw new NotFoundException("NOT FOUND");
		
		Comment savedComment = this.cs.save(comment, article.get(), user.get());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedComment.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateComment(@RequestBody Comment comment, @PathVariable("id") int id) {
		Optional<Comment> commentOptional = this.cs.findById(id);

		if (!commentOptional.isPresent())
			return ResponseEntity.notFound().build();

		comment = this.cs.update(comment, commentOptional.get());

		return ResponseEntity.accepted().build();
	}

	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable("id") int id){
		this.cs.deleteById(id);
	}
}
