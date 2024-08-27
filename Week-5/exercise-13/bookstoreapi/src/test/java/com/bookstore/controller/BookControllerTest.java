package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("19.99"));

        given(bookService.findById(1L)).willReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.isbn").value("1234567890"))
                .andExpect(jsonPath("$.price").value(19.99));
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setIsbn("0987654321");
        book.setPrice(new BigDecimal("29.99"));

        Book createdBook = new Book();
        createdBook.setId(1L);
        createdBook.setTitle("New Title");
        createdBook.setAuthor("New Author");
        createdBook.setIsbn("0987654321");
        createdBook.setPrice(new BigDecimal("29.99"));

        given(bookService.save(book)).willReturn(createdBook);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.isbn").value("0987654321"))
                .andExpect(jsonPath("$.price").value(29.99));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book bookDetails = new Book();
        bookDetails.setTitle("Updated Title");
        bookDetails.setAuthor("Updated Author");
        bookDetails.setIsbn("1234567890");
        bookDetails.setPrice(new BigDecimal("25.99"));

        Book updatedBook = new Book();
        updatedBook.setId(1L);
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("1234567890");
        updatedBook.setPrice(new BigDecimal("25.99"));

        given(bookService.findById(1L)).willReturn(updatedBook);
        given(bookService.update(1L, bookDetails)).willReturn(updatedBook);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"))
                .andExpect(jsonPath("$.isbn").value("1234567890"))
                .andExpect(jsonPath("$.price").value(25.99));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("19.99"));

        given(bookService.findById(1L)).willReturn(book);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());

        then(bookService).should().delete(1L);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = List.of(
            new Book() {{
                setId(1L);
                setTitle("Title1");
                setAuthor("Author1");
                setIsbn("1234567890");
                setPrice(new BigDecimal("19.99"));
            }},
            new Book() {{
                setId(2L);
                setTitle("Title2");
                setAuthor("Author2");
                setIsbn("0987654321");
                setPrice(new BigDecimal("29.99"));
            }}
        );

        given(bookService.findAll()).willReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }
}
