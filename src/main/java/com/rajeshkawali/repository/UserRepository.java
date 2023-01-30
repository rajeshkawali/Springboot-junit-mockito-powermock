package com.rajeshkawali.repository;
import java.util.List;
import java.util.Map;

import com.rajeshkawali.model.User;

/**
 * @author Rajesh_Kawali
 * 
 */
public interface UserRepository {
	
	List<User> getAllUsers();

	User getUserById(Long id);

	User createUser(User user);

	User updateUser(Long id, User user);

	User patchUser(Long id, Map<String, Object> updates);

	boolean deleteUser(Long id);
	
	public User findByEmail(String email);
}