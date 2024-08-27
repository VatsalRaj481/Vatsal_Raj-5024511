package com.bookstore.integration;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookstoreIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test
        bookRepository.deleteAll();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        // Arrange
        Book book1 = new Book();
        book1.setTitle("Title1");
        book1.setAuthor("Author1");
        book1.setIsbn("1234567890");
        book1.setPrice(new BigDecimal("19.99"));
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Title2");
        book2.setAuthor("Author2");
        book2.setIsbn("0987654321");
        book2.setPrice(new BigDecimal("29.99"));
        bookRepository.save(book2);

        // Act & Assert
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void testCreateBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setIsbn("1111111111");
        book.setPrice(new BigDecimal("39.99"));

        // Act & Assert
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.isbn").value("1111111111"))
                .andExpect(jsonPath("$.price").value(39.99));
    }

    @Test
    public void testUpdateBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setTitle("Original Title");
        book.setAuthor("Original Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("19.99"));
        Book savedBook = bookRepository.save(book);

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("1234567890");
        updatedBook.setPrice(new BigDecimal("29.99"));

        // Act & Assert
        mockMvc.perform(put("/books/" + savedBook.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"))
                .andExpect(jsonPath("$.price").value(29.99));
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setTitle("Title to Delete");
        book.setAuthor("Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("19.99"));
        Book savedBook = bookRepository.save(book);

        // Act & Assert
        mockMvc.perform(delete("/books/" + savedBook.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/" + savedBook.getId()))
                .andExpect(status().isNotFound());
    }
}
