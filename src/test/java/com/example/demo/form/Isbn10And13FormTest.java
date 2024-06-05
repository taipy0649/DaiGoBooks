package com.example.demo.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class Isbn10And13FormTest {
	
	private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testIsbn10And13Valid() {
        Isbn10And13Form form = new Isbn10And13Form();
        form.setIsbn_10("1234567890");
        form.setIsbn_13("1234567890123");

        Set<ConstraintViolation<Isbn10And13Form>> violations = validator.validate(form);
        assertTrue(violations.isEmpty());
        assertTrue(form.isIsbn10OrIsbn13());
    }

    @Test
    void testIsbn10Invalid() {
        Isbn10And13Form form = new Isbn10And13Form();
        form.setIsbn_10("123");
        form.setIsbn_13("1234567890123");

        Set<ConstraintViolation<Isbn10And13Form>> violations = validator.validate(form);
        assertFalse(violations.isEmpty());
        assertTrue(form.isIsbn10OrIsbn13());
    }

    @Test
    void testIsbn13Invalid() {
        Isbn10And13Form form = new Isbn10And13Form();
        form.setIsbn_10("1234567890");
        form.setIsbn_13("123");

        Set<ConstraintViolation<Isbn10And13Form>> violations = validator.validate(form);
        assertFalse(violations.isEmpty());
        assertTrue(form.isIsbn10OrIsbn13());
    }

    @Test
    void testIsbn10And13BothInvalid() {
        Isbn10And13Form form = new Isbn10And13Form();
        form.setIsbn_10("123");
        form.setIsbn_13("123");

        Set<ConstraintViolation<Isbn10And13Form>> violations = validator.validate(form);
        assertFalse(violations.isEmpty());
        assertTrue(form.isIsbn10OrIsbn13());
    }

    @Test
    void testIsbn10And13BothEmpty() {
        Isbn10And13Form form = new Isbn10And13Form();
        form.setIsbn_10("");
        form.setIsbn_13("");

        Set<ConstraintViolation<Isbn10And13Form>> violations = validator.validate(form);
        assertFalse(violations.isEmpty());
        assertFalse(form.isIsbn10OrIsbn13());
    }

    @Test
    void testIsbn10And13BothNull() {
        Isbn10And13Form form = new Isbn10And13Form();
        form.setIsbn_10(null);
        form.setIsbn_13(null);

        Set<ConstraintViolation<Isbn10And13Form>> violations = validator.validate(form);
        assertFalse(violations.isEmpty());
        assertFalse(form.isIsbn10OrIsbn13());
    }

}
