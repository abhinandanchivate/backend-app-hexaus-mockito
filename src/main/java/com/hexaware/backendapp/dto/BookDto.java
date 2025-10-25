// src/main/java/com/example/demo/dto/BookDto.java
package com.hexaware.backendapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDto(
  @NotBlank String title,
  @NotBlank String isbn,
  @NotNull Long authorId
) {}
