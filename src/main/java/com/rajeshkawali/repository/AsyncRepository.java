package com.rajeshkawali.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rajeshkawali.model.ModelName;

/**
	@author Rajesh Kawali
*/

@Repository
public class AsyncRepository {

	public List<ModelName> fetchUserNames() {
		List<ModelName> userList = getAllUsers();
		return userList;
	}
	
	public ModelName fetchUserById(Integer id) {
		List<ModelName> userList = getAllUsers();
		Optional<ModelName> name = userList.stream().filter(u ->u.getId().equals(id)).findFirst();
		return name.get();
	}

	public List<ModelName> getAllUsers(){
		List<ModelName> userList = Arrays.asList(
		new ModelName(1, "Rajesh","Kawali"),
		new ModelName(2, "Mahesh","Kawali"),
		new ModelName(3, "Laksh","Kawali"),
		new ModelName(4, "Vinod","Kawali"));
		return userList;
	}

	public String fetchUserLastNameById(Integer id) {
		List<ModelName> userList = getAllUsers();
		Optional<ModelName> name = userList.stream().filter(u ->u.getId().equals(id)).findFirst();
		return name.get().getLastName();
	}
}
