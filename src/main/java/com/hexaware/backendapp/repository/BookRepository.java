// src/main/java/com/example/demo/repo/BookRepository.java
package com.hexaware.backendapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.backendapp.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByIsbn(String isbn);
  List<Book> findByAuthorId(Long authorId);
}
