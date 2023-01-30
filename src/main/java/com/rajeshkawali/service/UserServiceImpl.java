package com.rajeshkawali.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshkawali.model.User;
import com.rajeshkawali.repository.UserRepository;

/**
 * @author Rajesh_Kawali
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public User createUser(User user) {
		return userRepository.createUser(user);
	}

	@Override
	public User updateUser(Long id, User user) {
		user.setId(id);
		return userRepository.updateUser(id, user);
	}

	@Override
	public User patchUser(Long id, Map<String, Object> updates) {
		return userRepository.patchUser(id, updates);
	}

	@Override
	public boolean deleteUser(Long id) {
		return userRepository.deleteUser(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
