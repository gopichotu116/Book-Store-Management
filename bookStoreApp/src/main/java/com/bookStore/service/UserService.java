package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.User;
import com.bookStore.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	/* --------------------- Save signUp detail  ----------------------*/
	
	public void save(String name, String email, String pswd, String dept) {
		User user = new User(name, email, pswd, dept,0.0);
		userRepo.save(user);
	}
	
	/* --------------------- Save Update details ----------------------*/
	
	public void save(User u) {
		userRepo.save(u);
	}
	
	/* --------------------- Getting user details ----------------------*/
	
	public User getUserById(int id) {
		 return userRepo.findById(id).get();
	}
	
	public String getNameByEmail(String email) {
		return userRepo.findNameByEmail(email);
	}
	
	public User findByEmail(String username) {
		User user = userRepo.findByEmail(username);
		return user;
	}
	
	public User getIdByEmail(String email) {
		return userRepo.findIdByEmail(email);
		
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	/* --------------------- Login checkup ----------------------*/
	
	public boolean checkUserandPass(String name,String password) {
		User user = userRepo.findByEmailAndPswd(name, password);
		if(user==null) return false;
		return true;
	}

	public boolean getWalletByEmail(String userName) {
		return userRepo.findWalletByEmail(userName);
	}
	
}
