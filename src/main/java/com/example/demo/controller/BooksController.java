package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BooksController {

	@GetMapping()
	public String returnBookListAsHome(Model model) {
		return "book-list";
	}
	
	@GetMapping("/search")
	public String searchBook() {
        return "search-save";
    }
	
	@PostMapping("/search")
	public String searchBookResult(BindingResult bindingResult, RedirectAttributes attributes) {
		
		if (bindingResult.hasErrors()) {
			return "search-save";
		}
		
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
