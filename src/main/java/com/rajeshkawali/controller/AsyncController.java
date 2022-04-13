package com.rajeshkawali.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshkawali.model.ModelName;
import com.rajeshkawali.service.AsyncService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Rajesh Kawali
 * 
 */

@RestController
@RequestMapping("/api/v1")
@Api(value = "Spring boot async API")
public class AsyncController {

	public static final String CLASS_NAME = AsyncController.class.getName();
	private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	private AsyncService asyncService;

	@ApiOperation(value = "Fetch user details")
	@GetMapping(value = { "/fetchUsers" })
	public CompletableFuture<List<ModelName>> fetchUserNames() {
		String _function = ".fetchUserNames";
		log.info(CLASS_NAME + _function + "::ENTER");
		CompletableFuture<List<ModelName>> listName = null;
		try {
			System.out.println("Thread name: " + Thread.currentThread().getName());
			listName = asyncService.fetchUserNames();
			log.info(CLASS_NAME + _function + "::listName: "+ listName.get()); // this will block the thread until response arrive
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred while fetching user details: " + e.toString());
		}
		log.info(CLASS_NAME + _function + "::EXIT");
		return listName;
	}

	@ApiOperation(value = "Fetch user by id")
	@GetMapping(value = { "/fetchUserById" })
	public ModelName fetchUserById(@RequestParam(name = "id") Integer id) {
		String _function = ".fetchUserById";
		log.info(CLASS_NAME + _function + "::ENTER: id: " + id);
		ModelName userName = null;
		try {
			userName = asyncService.fetchUserById(id);
			log.info(CLASS_NAME + _function + "::userName: "+ userName.toString());
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred while fetching user details: " + e.toString());
		}
		log.info(CLASS_NAME + _function + "::EXIT");
		return userName;
	}
	
	@ApiOperation(value = "Fetch user's last name by id")
	@GetMapping(value = { "/fetchUserLastNameById" })
	private String fetchUserLastNameById(@RequestParam(name = "id") Integer id) {
		String _function = ".fetchUserLastNameById";
		log.info(CLASS_NAME + _function + "::ENTER: id: " + id);
		String userName = null;
		try {
			userName = asyncService.fetchUserLastNameById(id);
			log.info(CLASS_NAME + _function + "::userName: "+ userName.toString());
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred while fetching user details: " + e.toString());
		}
		log.info(CLASS_NAME + _function + "::EXIT");
		return userName;
	}
}
