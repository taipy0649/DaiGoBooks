package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookMapper;
import com.example.demo.service.BooksRepositoryService;

@Service
@Transactional
public class BooksRepositoryServiceImpl implements BooksRepositoryService {

	private final BookMapper bookMapper;
	
	public BooksRepositoryServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	@Override
	public List<Book> findAllBooks() {
		return bookMapper.selectAll();
	}

	@Override
	public boolean saveBook(Book book) {
		try {
			bookMapper.insert(book);

		} catch (Exception e) {
			System.err.println("Exception caught: " + e.getMessage());
			return false;
		}
		return true;
	}

}
