// src/main/java/com/example/demo/service/BookService.java
package com.hexaware.backendapp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.backendapp.domain.Author;
import com.hexaware.backendapp.domain.Book;
import com.hexaware.backendapp.repository.AuthorRepository;
import com.hexaware.backendapp.repository.BookRepository;

@Service
@Transactional
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public Book create(String title, String isbn, Long authorId) {
    bookRepository.findByIsbn(isbn).ifPresent(b -> {
      throw new IllegalArgumentException("ISBN already exists");
    });
    Author author = authorRepository.findById(authorId)
      .orElseThrow(() -> new IllegalArgumentException("Author not found"));
    Book book = new Book(title, isbn);
    book.setAuthor(author);
    return bookRepository.save(book);
  }

  @Transactional(readOnly = true)
  public Book get(Long id) {
    return bookRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Book not found"));
  }

  @Transactional(readOnly = true)
  public List<Book> byAuthor(Long authorId) {
    return bookRepository.findByAuthorId(authorId);
  }

  @Transactional(readOnly = true)
  public List<Book> all() { return bookRepository.findAll(); }
}
