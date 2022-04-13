package com.rajeshkawali.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rajeshkawali.model.ModelName;
import com.rajeshkawali.repository.AsyncRepository;

/**
 * @author Rajesh Kawali
 */

@Service
public class AsyncService {

	@Autowired
	private AsyncRepository asyncRepository;

	@Async("asyncExecutor")
	public CompletableFuture<List<ModelName>> fetchUserNames() {
		List<ModelName> userList = asyncRepository.fetchUserNames();
		System.out.println("Thread name - AsyncExecutor: " + Thread.currentThread().getName());
		return CompletableFuture.completedFuture(userList);
	}

	public ModelName fetchUserById(Integer id) {
		ModelName user = null;
		try {
			user = asyncRepository.fetchUserById(id);
		} catch (Exception e) {
			System.out.println("Exception occurred while fetching user details: " + e.toString());
		}
		return user;
	}

	public String fetchUserLastNameById(Integer id) {
		String lastName = null;
		try {
			lastName = asyncRepository.fetchUserLastNameById(id);
		} catch (Exception e) {
			System.out.println("Exception occurred while fetching user details: " + e.toString());
		}
		return lastName;
	}

}
