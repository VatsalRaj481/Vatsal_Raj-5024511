package com.bookstore.controller;

import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();
    private long currentId = 1;

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setId(currentId++);
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> existingBook = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            book.setIsbn(bookDetails.getIsbn());
            return book;
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    // Fetch a book by its ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null); // Returns null if book not found
    }

    // Filter books based on query parameters like title and author
    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                                (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .toList();
    }
}
