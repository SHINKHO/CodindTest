package com.book.manage.controller;

import com.book.manage.controller.dto.AuthorDTO;
import com.book.manage.domain.Author;
import com.book.manage.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class RestfulAuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthor();
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            AuthorDTO authorDTO = new AuthorDTO(author);
            return ResponseEntity.ok(authorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byFirstName/{firstName}")
    public ResponseEntity<List<AuthorDTO>> getAuthorsByFirstName(@PathVariable String firstName) {
        List<Author> authors = authorService.getAuthorByFirstName(firstName);
        return createResponse(authors);
    }

    @GetMapping("/byLastName/{lastName}")
    public ResponseEntity<List<AuthorDTO>> getAuthorsByLastName(@PathVariable String lastName) {
        List<Author> authors = authorService.getAuthorByLastName(lastName);
        return createResponse(authors);
    }

    @GetMapping("/contains/{name}")
    public ResponseEntity<List<AuthorDTO>> getAuthorsByNameContains(@PathVariable String name) {
        List<Author> authorsOfName = authorService.getAuthorContains(name);
        return createResponse(authorsOfName);
    }

    @PostMapping
    public ResponseEntity<Void> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        authorService.addAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO updatedAuthorDTO) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedAuthorDTO.getFirstName() != null) {
            existingAuthor.setFirstName(updatedAuthorDTO.getFirstName());
        }

        if (updatedAuthorDTO.getLastName() != null) {
            existingAuthor.setLastName(updatedAuthorDTO.getLastName());
        }

        authorService.modifyAuthor(existingAuthor.getId(), existingAuthor.getFirstName(), existingAuthor.getLastName());

        return ResponseEntity.ok(new AuthorDTO(existingAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            authorService.removeAuthor(author);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<List<AuthorDTO>> createResponse(List<Author> authors) {
        if (authors != null && !authors.isEmpty()) {
            List<AuthorDTO> authorDTOs = authors.stream()
                    .map(AuthorDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(authorDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
