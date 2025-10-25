package com.hexaware.backendapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Simple DTO for creating or updating Author.
 * Uses Java 17+ record syntax for immutability.
 */
public record AuthorDto(
        @NotBlank(message = "Author name is required")
        String name,

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is required")
        String email
) {}
