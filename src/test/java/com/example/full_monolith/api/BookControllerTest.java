package com.example.full_monolith.api;

import com.example.full_monolith.entity.Book;
import com.example.full_monolith.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for {@link BookController}.
 * The tests are using Spring Boot's testing support for testing the web layer.
 * The {@code @WebMvcTest} annotation is used to test only the web layer, not the whole context.
 *
 * This simple test scenario focuses on the existence and accessibility of the API endpoints.
 */

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final String API_URL = "/api/books";

    @Test
    void getAllBooks_shouldReturnAllBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1");
        Book book2 = new Book(2L, "Title2", "Author2");

        given(bookService.findAllBooks()).willReturn(List.of(book1, book2));

        mockMvc.perform(get(API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(book1.getId().intValue())))
                .andExpect(jsonPath("$[0].title", is(book1.getTitle())));
    }

    @Test
    void getAllBooks_WhenNoBooksExist_ShouldReturnEmptyList() throws Exception {
        given(bookService.findAllBooks()).willReturn(List.of());

        mockMvc.perform(get(API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() throws Exception {
        Book book = new Book(1L, "Title", "Author");

        given(bookService.findBookById(1L)).willReturn(Optional.of(book));

        mockMvc.perform(get(API_URL.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())))
                .andExpect(jsonPath("$.title", is(book.getTitle())));
    }

    @Test
    void getBookById_WhenBookDoesNotExists_ShouldReturnNotFound() throws Exception {
        given(bookService.findBookById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get(API_URL.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveBook_shouldReturnSavedBook() throws Exception {
        Book book = new Book(1L, "Title", "Author");

        given(bookService.saveBook(book)).willReturn(book);

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Title\",\"author\":\"Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())))
                .andExpect(jsonPath("$.title", is(book.getTitle())));
    }

//    @Test
//    void saveBook_WhenInvalidBookData_ShouldReturnBadRequest() throws Exception {
//        Book invalidBook = new Book(null, "", "");
//
//        mockMvc.perform(post(API_URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(invalidBook)))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    void deleteBookById_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete(API_URL.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBookById_WhenInvalidId_ShouldReturnNotFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(1)).when(bookService).deleteBookById(anyLong());

        mockMvc.perform(delete(API_URL.concat("/999"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
