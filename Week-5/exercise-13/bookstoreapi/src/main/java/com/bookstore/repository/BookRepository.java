package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by title
    List<Book> findByTitle(String title);

    // Find books by author
    List<Book> findByAuthor(String author);

    // Find books by title and author
    List<Book> findByTitleAndAuthor(String title, String author);

    // Optional: Find a book by its ISBN if you want a unique constraint
    Optional<Book> findByIsbn(String isbn);
}
