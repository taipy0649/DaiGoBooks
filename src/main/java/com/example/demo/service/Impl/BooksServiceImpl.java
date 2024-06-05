package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.api.BooksApiResponse;
import com.example.demo.externalApi.GoogleBooksApi;
import com.example.demo.service.BooksService;


@Service
@Transactional
public class BooksServiceImpl implements BooksService {
	
	private final GoogleBooksApi googleBooksApi;

	public BooksServiceImpl(GoogleBooksApi googleBooksApi) {
		this.googleBooksApi = googleBooksApi;
	}

	
	@Override
	public Book searchBookFromGoogleAPI(String isbn) {
		
		BooksApiResponse result = googleBooksApi.searchBookByIsbn(isbn);
		
		if (result.getTotalItems() != 1) {
			return null;
		}
		
		Book book = new Book();
		book.setTitle(result.getFirstItemTitle());
		book.setAuthor(result.getFirstItemAuthors());
		book.setImage_url(result.getFirstItemImageUrl());
		book.setIsbn_10(result.getFirstItemIsbn10());
		book.setIsbn_13(result.getFirstItemIsbn13());
		
		return book;	
	}
}
