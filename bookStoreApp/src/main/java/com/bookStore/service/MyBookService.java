package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.MyBooks;
import com.bookStore.repository.MyBookRepository;

@Service
public class MyBookService {
	
	@Autowired
	private MyBookRepository mybook;
	
	/* --------------------- Save books to cart ----------------------*/
	
	public void saveMyBooks(MyBooks book) {
		mybook.save(book);
	}
	
	/* --------------------- Cart details ----------------------*/
	
	public List<MyBooks> getAllMyBooks(){
		return mybook.findAll();
	}
	
	/* --------------------- Delete book from cart ----------------------*/
	
	public void deleteById(int id) {
		mybook.deleteById(id);
	}
}