package com.hexaware.backendapp.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hexaware.backendapp.domain.Author;
import com.hexaware.backendapp.repository.AuthorRepository;
import com.hexaware.backendapp.services.AuthorService;

@ExtendWith(MockitoExtension.class)

public class AuthorServiceTest {
	
	@Mock
	AuthorRepository authorRepository;
	
	@InjectMocks
	AuthorService authorService;
	
	@Test
	
	void create_saves_whenemail_unique() {
		when(authorRepository.findByEmail("a@gmail.com")).thenReturn(Optional.empty());
		when(authorRepository.save(any(Author.class)))
        .thenAnswer(inv -> { Author a = inv.getArgument(0); a.getClass(); return a; });
		
		Author created = authorService.create("Abhi", "a@gmail.com");
		

	}
	
	@Test
	void create_throws_when_email_exists() {
		when(authorRepository.findByEmail("a@x.com")).thenReturn(Optional.of(new Author("old","a@x.com")));
		assertThatThrownBy(()-> authorService.create("New","a@x.com"))
		.isInstanceOf(IllegalArgumentException.class)
		.hasMessageContaining("email already exists");
		verify(authorRepository,never()).save(any());
	}

}
