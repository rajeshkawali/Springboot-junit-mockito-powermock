package com.rajeshkawali.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.rajeshkawali.model.User;
import com.rajeshkawali.service.UserService;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	private User user;
	private List<User> users;
	private Map<String, Object> updates;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(userController, "userService", userService);
		
		System.out.println("Before");
		user = new User(1L, "Laksh", "laksh@gmail.com");
		users = Arrays.asList(user);
		updates = new HashMap<>();
		updates.put("name", "Laksh");
		updates.put("email", "laksh@gmail.com");
	}

	@Test
	public void getAllUsersTest() {
		Mockito.when(userService.getAllUsers()).thenReturn(users);
		ResponseEntity<List<User>> response = userController.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(users, response.getBody());
		verify(userService, times(1)).getAllUsers();
	}

	@Test
	public void getUserByIdTest() {
		when(userService.getUserById(user.getId())).thenReturn(user);
		ResponseEntity<User> response = userController.getUserById(user.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
		verify(userService, times(1)).getUserById(user.getId());
	}

	@Test
	public void getUserByIdNotFoundTest() {
		when(userService.getUserById(user.getId())).thenReturn(null);
		ResponseEntity<User> response = userController.getUserById(user.getId());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userService, times(1)).getUserById(user.getId());
	}

	@Test
	public void findByEmailTest() {
		when(userService.findByEmail(user.getEmail())).thenReturn(user);
		ResponseEntity<User> response = userController.findByEmail(user.getEmail());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
		verify(userService, times(1)).findByEmail(user.getEmail());
	}

	@Test
	public void findByEmailNotFoundTest() {
		when(userService.findByEmail(user.getEmail())).thenReturn(null);
		ResponseEntity<User> response = userController.findByEmail(user.getEmail());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userService, times(1)).findByEmail(null);
	}

}