package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Book;
import com.example.demo.form.IsbnForm;
import com.example.demo.service.BooksRepositoryService;
import com.example.demo.service.BooksService;

@Controller
@RequestMapping("/")
public class BooksController {

	private final BooksService booksService;
	private final BooksRepositoryService booksRepositoryService;
	
	public BooksController(BooksService booksService, BooksRepositoryService booksRepositoryService) {
		this.booksService = booksService;
		this.booksRepositoryService = booksRepositoryService;
	}
	
	@GetMapping()
	public String returnBookListAsHome(Model model) {
		model.addAttribute("books", booksRepositoryService.findAllBooks());
		return "book-list";
	}
	
	@GetMapping("/search")
	public String searchBook() {
        return "search-save";
    }
	
	@PostMapping("/search")
	public String searchBookResult(@Validated IsbnForm isbnForm, BindingResult bindingResult, RedirectAttributes attributes) {
		
		if (bindingResult.hasErrors()) {
			return "search-save";
		}
		
		Book book = booksService.searchBookFromGoogleAPI(isbnForm.getIsbn());
		attributes.addFlashAttribute("book", book);
		return "redirect:/search";
	}
	
	@PostMapping("/save")
	public String saveBook(BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/search";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/about")
	public String aobutPage() {
		return "about";
	}
	
	@GetMapping("/error")
	public String showErrorPage() {
		return "error";
	}
}
