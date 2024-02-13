package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bRepo;
	
	/* --------------------- Save book ----------------------*/
	
	public void save(Book b) {
		bRepo.save(b);
	}
	
	/* --------------------- Getting book details ----------------------*/
	
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}
	
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	
	/* --------------------- Delete book ----------------------*/
	
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}

	public List<Book> searchByValue(String value) {
		return bRepo.findByValue(value);
	}
}
