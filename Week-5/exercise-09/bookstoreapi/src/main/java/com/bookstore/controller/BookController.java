package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Book> resource = EntityModel.of(book);

        // Add a self-link to the book
        Link selfLink = linkTo(methodOn(this.getClass()).getBookById(id)).withSelfRel();
        resource.add(selfLink);

        // Add a link to get all books
        Link allBooksLink = linkTo(methodOn(this.getClass()).getAllBooks()).withRel("all-books");
        resource.add(allBooksLink);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Book>>> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<EntityModel<Book>> resources = books.stream()
                .map(book -> {
                    EntityModel<Book> resource = EntityModel.of(book);
                    Link selfLink = linkTo(methodOn(this.getClass()).getBookById(book.getId())).withSelfRel();
                    resource.add(selfLink);
                    return resource;
                }).toList();

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
