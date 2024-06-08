package com.example.demo.externalApi.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.api.BooksApiResponse;
import com.example.demo.externalApi.GoogleBooksApi;


@Service
public class GoogleBooksApiImpl implements GoogleBooksApi{
	private RestTemplate restTemplate = new RestTemplate();
	
	private final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:%s&key=%s";
	
//	@Value("${google_books.apikey}")
	private String apiKey = "AIzaSyABSkl0VF0rb0ozwE7nZLLZS7phgLyhWbk";
	
	@Override
	public BooksApiResponse searchBookByIsbn(String isbn) {
		
		String requestUrl = String.format(url, isbn, apiKey);
		BooksApiResponse result = restTemplate.getForObject(requestUrl, BooksApiResponse.class);		
		return result;
	}

}
