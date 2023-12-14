package com.user.fullStackbackend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.fullStackbackend.exception.UserNotFoundException;
import com.user.fullStackbackend.model.User;
import com.user.fullStackbackend.repository.UserRepository;

@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	 private UserRepository repo;
	
	@PostMapping("/user")
	
	public User user(@RequestBody User user)
	{
	    User users=repo.save(user)	;
	    return users;
	}
	
	@GetMapping("/users")
	public List<User> getAllusers()
	{
		return repo.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getAllUserbyId(@PathVariable int id)
	{
		return Optional.of(repo.findById(id) 
				.orElseThrow(() -> new UserNotFoundException(id)));
	}
	
	 @PutMapping("/user/{id}")
	    User updateUser(@RequestBody User newUser, @PathVariable int id) {
	        return repo.findById(id)
	        		.map(user -> {
	                    user.setUsername(newUser.getUsername());
	                    user.setName(newUser.getName());
	                    user.setEmail(newUser.getEmail());
	                    return repo.save(user);
	                }).orElseThrow(() -> new UserNotFoundException(id));
	    }

	    @DeleteMapping("/user/{id}")
	    String deleteUser(@PathVariable int id){
	        if(!repo.existsById(id)){
	            throw new UserNotFoundException(id);
	        }
	        repo.deleteById(id);
	        return  "User with id "+id+" has been deleted success.";
	    }



}
