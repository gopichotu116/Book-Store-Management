package com.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "pswd", nullable = false, unique= true)
	private String pswd;
	
	@Column(name = "dept", nullable = false)
	private String department;
	
	@Column(nullable = false)
	private double wallet;

	public User(String name, String email, String pswd, String department, double wallet) {
		super();
		this.name = name;
		this.email = email;
		this.pswd = pswd;
		this.department = department;
		this.wallet = wallet;
	}
	
	
	
}
