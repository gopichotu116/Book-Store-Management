package com.bookStore.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBooks;
import com.bookStore.entity.User;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookService;
import com.bookStore.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookService myBookService;
	

	public String userName;
	
	/* --------------------- Home page ----------------------*/

	@GetMapping("/home")
	public String home() {
		return "/user/home";
	}
	
	/* --------------------- SignUp Welcome page ----------------------*/
	
	@PostMapping("/signupwel")
	public String signupWel(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("pswd") String pswd, @RequestParam("dept") String dept) {
		if(passValidation(pswd)) {
			us.save(name, email, pswd, dept);
			return "/signup/signupWel";
		}
		return "/signup/signupFail";
	}
	
	/* --------------------- Login Welcome page ----------------------*/
	
	@PostMapping("/loginwel")
	public String loginwell(@RequestParam("email") String email, @RequestParam("pswd") String password, Model model) {
		if (email.equals("admin@gmail.com") && password.equals("123")) {
			return "/admin/admin_login";
		}
		if (us.checkUserandPass(email, password)) {
			model.addAttribute("username", email);
			userName = (String) model.getAttribute("username");
			model.addAttribute("name", userName);
			return "redirect:/home";
			// take a look once
		}
		return "/login/loginFail";
	}
	
	/* --------------------- My Profile ----------------------*/
	
	@GetMapping("/my_profile")
	public String myProfile(Model model) {
		User user = us.findByEmail(userName);
		model.addAttribute("profile", user);
		return "/user/myProfile";
	}
	

	@GetMapping("/editProfile/{id}")
	public String editProfile(@PathVariable("id") int id, Model model) {
		User user = us.getUserById(id);
		model.addAttribute("userProfile", user);
		return "/user/editProfile";
	}
	
	/* --------------------- Wallet ----------------------*/
	
	@GetMapping("my_wallet")
	public String myWallet(Model model) {
		User user = us.findByEmail(userName);
		double money = user.getWallet();
		model.addAttribute("wallet_money", money);
		return "/wallet/wallet";
	}
	
	/* --------------------- Add Money to Wallet ----------------------*/
	
	@PostMapping("addMoney")
	public String addMoney(@RequestParam("wallet") int wallet, Model model) {
		User user = us.findByEmail(userName);
		user.setWallet(wallet+user.getWallet());
		us.save(user);
		return "/wallet/addMoney";
	}
	
	/* --------------------- Cart(My Books) ----------------------*/
	
	@GetMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id, Model model) {
		Book b=bookService.getBookById(id);
		User user = us.getIdByEmail(userName);
		MyBooks mb=new MyBooks(b.getId(),b.getName(),b.getAuthor(),b.getPrice(),user);
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	/* --------------------- Purchase Book ----------------------*/
	
	@GetMapping("/purchase/{price}")
	public String purchaseBook(@PathVariable("price") double price) {
		User user = us.findByEmail(userName);
		double money = user.getWallet();
		if(money<price) return "/wallet/noFunds";
		else {
			user.setWallet(user.getWallet()-price);
			us.save(user);
			return "/wallet/purchaseSuccess";
		}
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBooks>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		User user = us.findByEmail(userName);
		double wallet = user.getWallet();
		model.addAttribute("money", wallet);
		return "/cart/myBooks";
	}
	
	
	/* --------------------- Update password ----------------------*/
	
	@PostMapping("/changePass")
	public String changePass(@RequestParam("email") String email, Model model) {
		User user = us.getIdByEmail(email);
		if(user==null) return "/user/invalidEmail";
		else {
			model.addAttribute("password", user.getPswd());
			return "/password/showPassword";
		}
	}
	
	/* --------------------- Forget password ----------------------*/
	
	@GetMapping("/forgetPass")
	public String forgetPass() {
		return "/password/forgetPass";
	}
	
	/* --------------------- Save user after edit ----------------------*/
	
	@PostMapping("/saveUser")
	public String updateUser(@ModelAttribute User u, Model model) {
		model.addAttribute("profile", u);
		if(passValidation(u.getPswd())) {
			double wallet = u.getWallet();
			u.setWallet(wallet);
			us.save(u);
			return "/user/myprofile";
		}
		return "/password/invalidPass";
	}
	
	/* --------------------- Password validation ----------------------*/
	
	public boolean passValidation(String pass) {
		String regexp = "(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=.*[0-9]).{5,16}";
		Matcher m = Pattern.compile(regexp).matcher(pass);
		return m.matches();

	}
	
}