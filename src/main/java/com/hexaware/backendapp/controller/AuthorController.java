// src/main/java/com/example/demo/web/AuthorController.java
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

import com.hexaware.backendapp.domain.Author;
import com.hexaware.backendapp.dto.AuthorDto;
import com.hexaware.backendapp.services.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
  private final AuthorService authorService;
  public AuthorController(AuthorService authorService) { this.authorService = authorService; }

  @PostMapping
  public ResponseEntity<Author> create(@RequestBody @Valid AuthorDto dto) {
    Author saved = authorService.create(dto.name(), dto.email());
    return ResponseEntity.created(URI.create("/api/authors/" + saved.getId())).body(saved);
  }

  @GetMapping("/{id}")
  public Author get(@PathVariable Long id) { return authorService.get(id); }

  @GetMapping
  public List<Author> all() { return authorService.all(); }
}
