package com.jmdev.crazytrip.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmdev.crazytrip.model.User;
import com.jmdev.crazytrip.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService us;

	@GetMapping("")
	public Iterable<User> findAllUsers() {
		return this.us.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> findById(@PathVariable("id") int id){
		return this.us.findById(id);
	}
	
	@GetMapping("/search")
	public List<User> findByString(@RequestParam("search") String search){
		return this.us.findByString(search);
	}

	@PostMapping("")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = this.us.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable("id") int id) {
		Optional<User> userOptional = this.us.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user = this.us.update(user, userOptional.get());

		return ResponseEntity.accepted().build();
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id){
		this.us.deleteById(id);
	}
}
