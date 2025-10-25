// src/main/java/com/example/demo/repo/AuthorRepository.java
package com.hexaware.backendapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.backendapp.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  Optional<Author> findByEmail(String email);
}
