package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.User;
import com.bookStore.service.BookService;
import com.bookStore.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private UserService us;
	
	/* --------------------- Admin Home Page ----------------------*/
	@GetMapping("/admin_home")
	public String adminHome() {
		return "/admin/admin_login";
	}
	
	/* --------------------- New Book Registration ----------------------*/
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "/admin/bookRegister";
	}
	
	/* --------------------- Save New Book ----------------------*/
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/available_books2";
	}
	
	/* --------------------- Available books in store ----------------------*/
	
	@GetMapping("/available_books2")
	public ModelAndView getAllBooks() {
		List<Book>list=service.getAllBook();
		return new ModelAndView("/admin/adminBookList","books",list);
	}
	
	/* --------------------- Edit book ----------------------*/
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "/admin/editBook";
	}
	
	/* --------------------- Delete Book ----------------------*/
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books2";
	}
	
	/* --------------------- Users List ----------------------*/
	
	@GetMapping("/getAllProfiles")
	public String getAllProfiles(Model model) {
		List<User> list = us.getAllUsers();
		model.addAttribute("users", list);
		return "admin/userProfile";
	}
}
