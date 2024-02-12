package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	
	String findNameByEmail(String name);
	
	User findByEmailAndPswd(String email, String pswd);

	User findByEmail(String name);
	
	User findIdByEmail(String email);

	Boolean findWalletByEmail(String userName);
	
}
