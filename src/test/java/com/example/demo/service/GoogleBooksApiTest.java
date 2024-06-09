package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.api.BooksApiResponse;
import com.example.demo.entity.api.Items;
import com.example.demo.entity.api.VolumeInfo;
import com.example.demo.externalApi.Impl.GoogleBooksApiImpl;

@ExtendWith(MockitoExtension.class)
public class GoogleBooksApiTest {

	@InjectMocks
	GoogleBooksApiImpl googleBooksApi;

	@Mock
	RestTemplate restTemplate;
	
	private final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:%s&key=%s";
	
	@Value("${GOOGLE_BOOKS_API_KEY}")
	private String apiKey;
	

	@Test
	void searchBookByIsbn_ShouldReturnBooksApiResponse_WithValidIsbn() {

		// Arrange
		List<Items> items = new ArrayList<>();
		Items item = new Items();
		VolumeInfo volumeInfo = new VolumeInfo();
		volumeInfo.setTitle("Some Book Title");
		item.setVolumeInfo(volumeInfo);
		items.add(item);
		
		String isbn = "1234567890";
		String requestUrl = String.format(url, isbn, apiKey);
		BooksApiResponse TempApiRespone = new BooksApiResponse("books#responses", 1, items);
		doReturn(TempApiRespone).when(restTemplate).getForObject(
				requestUrl,
				BooksApiResponse.class);

		// Act
		BooksApiResponse response = googleBooksApi.searchBookByIsbn(isbn);

		// Assert
		assertEquals("Some Book Title", response.getFirstItemTitle());
	}

	
	
}
