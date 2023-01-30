package com.rajeshkawali.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rajeshkawali.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * @author Rajesh_Kawali
 * 
 */
@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findByEmail(String email) {
		return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
				.setParameter("email", email).getSingleResult();
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User createUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public User updateUser(Long id, User user) {
		User managedUser = entityManager.find(User.class, id);
		if (managedUser != null) {
			managedUser.setName(user.getName());
			managedUser.setEmail(user.getEmail());
			entityManager.flush();
		}
		return managedUser;
	}

	@Override
	public User patchUser(Long id, Map<String, Object> updates) {
		User managedUser = entityManager.find(User.class, id);
		if (managedUser != null) {
			if (updates.containsKey("name")) {
				managedUser.setName((String) updates.get("name"));
			}
			if (updates.containsKey("email")) {
				managedUser.setEmail((String) updates.get("email"));
			}
			entityManager.flush();
		}
		return managedUser;
	}

	@Override
	public boolean deleteUser(Long id) {
		User managedUser = entityManager.find(User.class, id);
		if (managedUser != null) {
			entityManager.remove(managedUser);
			return true;
		}
		return false;
	}

}