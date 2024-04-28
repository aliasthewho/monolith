package com.example.full_monolith.service;

import com.example.full_monolith.BookRepository;
import com.example.full_monolith.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void saveBook_savesAndReturnsBook() {
        Book book = new Book(null, "1984", "George Orwell");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        assertThat(savedBook).isNotNull();
        verify(bookRepository).save(book);
    }

    @Test
    void findBookById_returnsBook() {
        Book book = new Book(1L, "1984", "George Orwell");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        var foundBook = bookService.findBookById(1L);

        assertThat(foundBook).isPresent();
        assertThat(foundBook.get()).isEqualTo(book);
        verify(bookRepository).findById(1L);
    }

    @Test
    void findAllBooks_returnsAllBooks() {
        when(bookRepository.findAll()).thenReturn(java.util.List.of(
                new Book(1L, "1984", "George Orwell"),
                new Book(2L, "Animal Farm", "George Orwell")
        ));

        var books = bookService.findAllBooks();

        assertThat(books).hasSize(2);
        verify(bookRepository).findAll();
    }

    @Test
    void deleteBookById_deletesBook() {
        bookService.deleteBookById(1L);
        verify(bookRepository).deleteById(1L);
    }
}
