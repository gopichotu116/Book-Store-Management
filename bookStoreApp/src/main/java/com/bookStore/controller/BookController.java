package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	/* --------------------- Book List for Users ----------------------*/
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=bookService.getAllBook();
		return new ModelAndView("/books/bookList","book",list);
	}
	
	
	
	
}
