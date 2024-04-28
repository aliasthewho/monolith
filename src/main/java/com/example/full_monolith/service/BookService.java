package com.example.full_monolith.service;

import com.example.full_monolith.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book saveBook(Book book);
    Optional<Book> findBookById(Long id);
    List<Book> findAllBooks();
    void deleteBookById(Long id);
}
