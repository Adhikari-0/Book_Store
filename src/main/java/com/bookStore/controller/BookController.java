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
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
//import com.fasterxml.jackson.annotation.JsonCreator.Mode;

//import jakarta.websocket.server.PathParam;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookListService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {	
		return "bookRegister";	
	}
	
	@GetMapping("/availabel_books")
	public ModelAndView getAllBook() {
		List<Book> list = service.getAllBooks();
//		ModelAndView mView = new ModelAndView();
//		System.out.println(list);
//		mView.setViewName("bookList");
//		mView.addObject("book",list);
//		return mView;
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/availabel_books";
	}
	@GetMapping("/my_book")
	public String getMyBook(Model model) {
		List<MyBookList> list = myBookListService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBook";
	}
	//@RequestMapping("/myList/{id}")
	@GetMapping("/myList/{id}")
	public String getMyList(@PathVariable("id") int id) {
		//System.out.println("The Id is: "+id);
		Book book = service.getBookById(id);
		MyBookList myBookList = new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
		myBookListService.saveMyBooks(myBookList);
		return "redirect:/my_book";
	}
	
	//@RequestMapping("/editBook/{id}")
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = service.getBookById(id);
		model.addAttribute("book",book);
		return "bookEdit";		
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/availabel_books";
		
	}
	

}
