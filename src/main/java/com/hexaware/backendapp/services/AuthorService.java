// src/main/java/com/example/demo/service/AuthorService.java
package com.hexaware.backendapp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.backendapp.domain.Author;
import com.hexaware.backendapp.repository.AuthorRepository;

@Service
@Transactional
public class AuthorService {
  private final AuthorRepository authorRepository;
  public AuthorService(AuthorRepository authorRepository) { this.authorRepository = authorRepository; }

  public Author create(String name, String email) {
    authorRepository.findByEmail(email).ifPresent(a -> {
      throw new IllegalArgumentException("Author email already exists");
    });
    return authorRepository.save(new Author(name, email));
  }

  @Transactional(readOnly = true)
  public Author get(Long id) {
    return authorRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Author not found"));
  }

  @Transactional(readOnly = true)
  public List<Author> all() { return authorRepository.findAll(); }
}
