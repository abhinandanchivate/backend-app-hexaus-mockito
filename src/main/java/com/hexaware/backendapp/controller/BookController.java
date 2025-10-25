// src/main/java/com/example/demo/web/BookController.java
package com.hexaware.backendapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.backendapp.domain.Book;
import com.hexaware.backendapp.dto.BookDto;
import com.hexaware.backendapp.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService bookService;
  public BookController(BookService bookService) { this.bookService = bookService; }

  @PostMapping
  public ResponseEntity<Book> create(@RequestBody @Valid BookDto dto) {
    Book saved = bookService.create(dto.title(), dto.isbn(), dto.authorId());
    return ResponseEntity.created(URI.create("/api/books/" + saved.getId())).body(saved);
  }

  @GetMapping("/{id}")
  public Book get(@PathVariable Long id) { return bookService.get(id); }

  @GetMapping
  public List<Book> all() { return bookService.all(); }

  @GetMapping("/by-author/{authorId}")
  public List<Book> byAuthor(@PathVariable Long authorId) {
    return bookService.byAuthor(authorId);
  }
}
