package com.book.manage.controller;

import com.book.manage.controller.dto.BookDTO;
import com.book.manage.domain.Book;
import com.book.manage.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class RestfulBookController {
    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = new LinkedList<>();

        for (Book book : books) {
            bookDTOs.add(new BookDTO(book));
        }

        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) {
        Book book = bookService.getBook(id);
        if (book != null) {
            BookDTO bookDTO = new BookDTO(book);
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/InRange/{from}/{to}")
    public ResponseEntity<List<BookDTO>> getBookInRange(@PathVariable LocalDate from,LocalDate to){
        List<Book> books = bookService.getBookInRange(from,to);
        if (books != null && !books.isEmpty()) {
            List<BookDTO> bookDTOs = books.stream()
                    .map(BookDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(bookDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        if (bookDTO != null) {
            Book book = new Book(
                    bookDTO.getIsbn(),
                    bookDTO.getTitle(),
                    bookDTO.getBookVersion(),
                    bookDTO.getBookCount(),
                    bookDTO.getWrittenDate(),
                    bookDTO.getKdcCode(),
                    bookDTO.getAuthor()
            );
            bookService.addBook(book);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable String id, @RequestBody BookDTO updatedBookDTO) {
        Book existingBook = bookService.getBook(id);
        if (existingBook == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (updatedBookDTO.getTitle() != null) {
            existingBook.setTitle(updatedBookDTO.getTitle());
        }

        if (updatedBookDTO.getAuthor() != null) {
            existingBook.setAuthor(updatedBookDTO.getAuthor());
        }

        if (updatedBookDTO.getBookVersion() != null) {
            existingBook.setBookVersion(updatedBookDTO.getBookVersion());
        }
        if (updatedBookDTO.getWrittenDate() != null) {
            existingBook.setWrittenDate(updatedBookDTO.getWrittenDate());
        }
        if (updatedBookDTO.getKdcCode() != null) {
            existingBook.setKdcCode(updatedBookDTO.getKdcCode());
        }
        if (updatedBookDTO.getBookCount() != null) {
            existingBook.setBookCount(updatedBookDTO.getBookCount());
        }

        bookService.modifyBook(existingBook.getIsbn(),
                existingBook.getTitle(),
                existingBook.getBookVersion(),
                existingBook.getBookCount(),
                existingBook.getWrittenDate(),
                existingBook.getKdcCode(),
                existingBook.getAuthor());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        Book book = bookService.getBook(id);
        if (book != null) {
            bookService.removeBook(book);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
