// src/main/java/com/example/demo/domain/Author.java
package com.hexaware.backendapp.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Author {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) private String name;
  @Column(nullable = false) private String email;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Book> books = new ArrayList<>();

  public Author() {}
  public Author(String name, String email) { this.name = name; this.email = email; }

  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public List<Book> getBooks() { return books; }

  public void addBook(Book b) { books.add(b); b.setAuthor(this); }
  public void removeBook(Book b) { books.remove(b); b.setAuthor(null); }

  @Override public boolean equals(Object o) {
    if (this == o) return true; if (!(o instanceof Author a)) return false;
    return Objects.equals(id, a.id);
  }
  @Override public int hashCode() { return Objects.hashCode(id); }
}
