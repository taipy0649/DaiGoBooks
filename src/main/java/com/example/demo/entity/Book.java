package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private String id;
	private String title;
	private String isbn_10;
	private String isbn_13;
	private String image_url;
	private String author;
	private Integer recommended_counts;
	private LocalDateTime created_at;
}
