package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookStore.entity.MyBooks;
import com.bookStore.service.MyBookService;

@Controller
public class MyBookListController {
	
	@Autowired
	private MyBookService myBookService;
	
	
	/* --------------------- Cart Book List ----------------------*/
	
	
	
	/* --------------------- Delete Book from List ----------------------*/
	
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		myBookService.deleteById(id);
		return "redirect:/my_books";
	}
	
}
