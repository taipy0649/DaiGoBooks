package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Book;
import com.example.demo.service.BooksService;


@WebMvcTest(BooksController.class)
public class BooksControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	BooksService booksService;
	
	@Test
	public void returnBookListAsHomeTest() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("book-list"));
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
}
