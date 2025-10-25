// src/main/java/com/example/demo/domain/Book.java
package com.hexaware.backendapp.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) private String title;
  @Column(nullable = false) private String isbn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;

  public Book() {}
  public Book(String title, String isbn) { this.title = title; this.isbn = isbn; }

  public Long getId() { return id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getIsbn() { return isbn; }
  public void setIsbn(String isbn) { this.isbn = isbn; }
  public Author getAuthor() { return author; }
  public void setAuthor(Author author) { this.author = author; }

  @Override public boolean equals(Object o) {
    if (this == o) return true; if (!(o instanceof Book b)) return false;
    return Objects.equals(id, b.id);
  }
  @Override public int hashCode() { return Objects.hashCode(id); }
}
