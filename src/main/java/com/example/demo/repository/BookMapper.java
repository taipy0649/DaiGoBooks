package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Book;

//@Mapper
public interface BookMapper {
	List<Book> selectAll();
	void insert(Book book);
}
