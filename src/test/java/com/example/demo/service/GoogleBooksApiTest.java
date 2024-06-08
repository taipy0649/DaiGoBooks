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

	@Test
	void searchBookByIsbn_ShouldReturnBooksApiResponse_WithValidIsbn() {

		// Arrange
		List<Items> items = new ArrayList<>();
		Items item = new Items();
		VolumeInfo volumeInfo = new VolumeInfo();
		volumeInfo.setTitle("Some Book Title");
		item.setVolumeInfo(volumeInfo);
		items.add(item);

		BooksApiResponse TempApiRespone = new BooksApiResponse("books#responses", 1, items);
		doReturn(TempApiRespone).when(restTemplate).getForObject(
				"https://www.googleapis.com/books/v1/volumes?q=isbn:1234567890&key=AIzaSyABSkl0VF0rb0ozwE7nZLLZS7phgLyhWbk",
				BooksApiResponse.class);

		// Act
		String isbn = "1234567890";
		BooksApiResponse response = googleBooksApi.searchBookByIsbn(isbn);

		// Assert
		assertEquals("Some Book Title", response.getFirstItemTitle());
	}

	
	
}
