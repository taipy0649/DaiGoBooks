package com.example.demo.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Isbn10And13Form {
	@Size(min=10, max=10)
	private String isbn_10;
	
	@Size(min=13, max=13)
	private String isbn_13;
	
	@AssertTrue
	public boolean isIsbn10OrIsbn13() {
		
		if (isbn_10 == null || isbn_10.isEmpty()) {
			return false;
		}
		
		if (isbn_13 == null || isbn_13.isEmpty()) {
			return false;
		}
		
		return true;
	}
}
