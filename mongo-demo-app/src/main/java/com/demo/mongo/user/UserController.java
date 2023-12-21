package com.demo.mongo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/{name}")
	public User findByName(@PathVariable String name) {
		return userService.findByName(name).orElse(null);
	}

	@PutMapping("/{name}")
	public User updateAddress(@PathVariable String name, @RequestBody String address) {
		return userService.updateUserAddress(name, address);
	}

	@DeleteMapping("/{name}")
	public void deleteUser(@PathVariable String name) {
		userService.deleteUser(name);
	}
}
