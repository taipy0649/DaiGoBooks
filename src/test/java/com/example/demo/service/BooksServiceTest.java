package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Book;
import com.example.demo.entity.api.BooksApiResponse;
import com.example.demo.entity.api.Items;
import com.example.demo.entity.api.VolumeInfo;
import com.example.demo.externalApi.GoogleBooksApi;
import com.example.demo.service.Impl.BooksServiceImpl;

public class BooksServiceTest {
	@Mock
	private GoogleBooksApi googleBooksApi;

	@InjectMocks
	private BooksServiceImpl booksServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSearchBookFromGoogleAPI_Success() {
		List<Items> items = new ArrayList<>();
		Items item = new Items();
		VolumeInfo volumeInfo = new VolumeInfo();
		volumeInfo.setTitle("Some Book Title");
		item.setVolumeInfo(volumeInfo);
		items.add(item);

		BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);
		String isbn = "1234567890";
		doReturn(response).when(googleBooksApi).searchBookByIsbn(isbn);
		
		// Act
		Book result = booksServiceImpl.searchBookFromGoogleAPI(isbn);
		assertEquals("Some Book Title", result.getTitle());
	}

	@Test
	void testSearchBookFromGoogleAPI_NoResults() {
		// Arrange
		String isbn = "1234567890";
		BooksApiResponse response = new BooksApiResponse();
		response.setTotalItems(0);

		doReturn(response).when(googleBooksApi).searchBookByIsbn(isbn);

		// Act
		Book result = booksServiceImpl.searchBookFromGoogleAPI(isbn);

		// Assert
		assertNull(result);
	}

	@Test
	void testSearchBookFromGoogleAPI_MultipleResults() {
		// Arrange
		String isbn = "1234567890";
		BooksApiResponse response = new BooksApiResponse();
		response.setTotalItems(2);

		doReturn(response).when(googleBooksApi).searchBookByIsbn(isbn);

		// Act
		Book result = booksServiceImpl.searchBookFromGoogleAPI(isbn);

		// Assert
		assertNull(result);
	}
}
