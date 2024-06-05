package com.example.demo.externalApi;

import com.example.demo.entity.api.BooksApiResponse;

public interface GoogleBooksApi {
	BooksApiResponse searchBookByIsbn(String isbn);
}
