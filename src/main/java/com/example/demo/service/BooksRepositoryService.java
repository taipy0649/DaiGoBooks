package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Book;

public interface BooksRepositoryService {

	List<Book> findAllBooks();
	boolean saveBook(Book book);
}
