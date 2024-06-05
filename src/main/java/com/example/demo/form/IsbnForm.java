package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IsbnForm {

	@NotBlank
	@Size(min = 10, max = 13)
	@Pattern(regexp = "^[0-9]{10}|[0-9]{13}$")
	private String isbn;
	
}
