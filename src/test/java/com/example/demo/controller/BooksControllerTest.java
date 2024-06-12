package com.example.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Book;
import com.example.demo.service.BooksRepositoryService;
import com.example.demo.service.BooksService;


@WebMvcTest(BooksController.class)
public class BooksControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	BooksService booksService;
	
	@MockBean
	BooksRepositoryService booksRepositoryService;
	
	@Test
	public void returnBookListAsHomeTest() throws Exception {
		
		Book book1 = new Book(
				"1",
				"Effective Java",
				"0134685997",
				"9780134685991",
				"http://example.com/effective_java.jpg",
				"Joshua Bloch",
				150,
				LocalDateTime.now());

		Book book2 = new Book(
				"2",
				"Clean Code",
				"0132350882",
				"9780132350885",
				"http://example.com/clean_code.jpg",
				"Robert C. Martin",
				200,
				LocalDateTime.now());

		Book book3 = new Book(
				"3",
				"The Pragmatic Programmer",
				"020161622X",
				"9780201616224",
				"http://example.com/pragmatic_programmer.jpg",
				"Andrew Hunt, David Thomas",
				180,
				LocalDateTime.now());

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.add(book3);

		doReturn(books).when(booksRepositoryService).findAllBooks();
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("book-list"))
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attribute("books", hasSize(3)));
	}
	
	@Test
	void searchBook_ShouldReturnSearchSaveView() throws Exception {
		mockMvc.perform(get("/search"))
				.andExpect(status().isOk())
				.andExpect(view().name("search-save"));
	}
	
	@Test
	void searchBook_InvalidEndpoint_ShouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/invalid"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void searchBook_MethodNotAllowed_ShouldReturnMethodNotAllowed() throws Exception {
		mockMvc.perform(put("/search"))
				.andExpect(status().isMethodNotAllowed());
	}
	
    @Test
    void testSearchBookResult_withInvalidIsbn() throws Exception {
        mockMvc.perform(post("/search")
                        .param("isbn", "invalidisbn"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("isbnForm", "isbn"))
                .andExpect(view().name("search-save"));

        verifyNoInteractions(booksService);
    }
	
	@Test
	void searchBookResult_ShouldReturnSearchSaveView() throws Exception {
		Book mockBook = new Book();
		mockBook.setId("123");
		mockBook.setTitle("Mock Title");
		mockBook.setIsbn_10("1234567890");
		mockBook.setIsbn_13("1234567890123");
		mockBook.setImage_url("http://example.com/image.jpg");
		mockBook.setAuthor("Mock Author");
		mockBook.setRecommended_counts(0);
		mockBook.setCreated_at(LocalDateTime.now());

		doReturn(mockBook).when(booksService).searchBookFromGoogleAPI(anyString());

		mockMvc.perform(post("/search")
				.param("isbn", "1234567890"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/search"))
				.andExpect(flash().attribute("book", mockBook));
	}
	
	@Test
	public void testAboutPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }
	
	@Test
    public void testShowErrorPage() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
	
	@Test
	void testSaveBook_withIsbn10() throws Exception {
		Book book = new Book();

		doReturn(book).when(booksService).searchBookFromGoogleAPI("1234567890");
		doReturn(true).when(booksRepositoryService).saveBook(book);

		mockMvc.perform(post("/save")
				.param("isbn_10", "1234567890")
				.param("isbn_13", ""))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/search"));
	}
	
	@Test
	void testSaveBook_withIsbn13() throws Exception {
		Book book = new Book();

		doReturn(book).when(booksService).searchBookFromGoogleAPI("1234567890123");
		doReturn(true).when(booksRepositoryService).saveBook(book);
		mockMvc.perform(post("/save")
				.param("isbn_10", "")
				.param("isbn_13", "1234567890123"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/search"));
	}

	@Test
    void testSaveBook_withEmptyIsbn() throws Exception {
        mockMvc.perform(post("/save")
                        .param("isbn_10", "")
                        .param("isbn_13", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/search"));

        // 検証: searchBookFromGoogleAPI と saveBook は呼ばれない
        Mockito.verifyNoInteractions(booksService);
        Mockito.verifyNoInteractions(booksRepositoryService);
	}

}
