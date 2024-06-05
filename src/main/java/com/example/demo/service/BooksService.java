package com.example.demo.service;

import com.example.demo.entity.Book;

public interface BooksService {

	Book searchBookFromGoogleAPI(String isbn);

}
