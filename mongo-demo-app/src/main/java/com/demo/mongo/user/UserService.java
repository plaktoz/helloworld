package com.demo.mongo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findByName(String name) {
		return Optional.ofNullable(userRepository.findByName(name));
	}

	public User updateUserAddress(String name, String newAddress) {
		User user = userRepository.findByName(name);
		if (user != null) {
			user.setAddress(newAddress);
			userRepository.save(user);
		}
		return user;
	}

	public void deleteUser(String name) {
		User user = userRepository.findByName(name);
		if (user != null) {
			userRepository.delete(user);
		}
	}
}
