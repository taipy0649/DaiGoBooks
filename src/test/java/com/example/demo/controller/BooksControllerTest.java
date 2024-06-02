package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(BooksController.class)
public class BooksControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
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
