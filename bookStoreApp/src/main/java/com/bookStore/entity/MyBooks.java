package com.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="my_books")
@Data
@NoArgsConstructor
public class MyBooks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name="u_id")
	@Cascade(CascadeType.MERGE)
	private User user;

	public MyBooks(int id, String name, String author, double price, User user) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.user = user;
	}
	
	
	
}
