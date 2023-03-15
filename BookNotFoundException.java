package com.example.demo;

public class BookNotFoundException extends RuntimeException{
    BookNotFoundException(Long id) {
        super("Could not find the book " + id);
    }
}
