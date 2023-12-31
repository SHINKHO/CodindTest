package com.book.manage.service;

import com.book.manage.domain.Author;
import com.book.manage.domain.Book;
import com.book.manage.domain.KdcCode;
import com.book.manage.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Transactional
    public void addBook(Book book){
        try{
            bookRepository.save(book);
        }catch(Exception e){
            log.error("Error occurred while adding book: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while adding book", e);
        }
    }

    @Transactional
    public void modifyBook(String isbn, String title, Short version, Short count, LocalDate date, KdcCode code, Author author){
        try{
            Optional<Book> bookOpt = bookRepository.findById(isbn);
            Book book = bookOpt.orElse(null);
            assert book != null;
            book.setTitle(title);
            book.setBookVersion(version);
            book.setBookCount(count);
            book.setWrittenDate(date);
            book.setAuthor(author);
            book.setKdcCode(code);
        }catch(Exception e){
            log.error("Error occurred while modifying book: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while modifying book", e);
        }
    }
    @Transactional(readOnly = true)
    public Book getBook(String id){
        try{
            bookRepository.findById(id);
        }catch(Exception e){
            log.error("Error occurred while fetching book by ID: {}", id, e);
            throw new RuntimeException("Error occurred while fetching book by ID: " + id, e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Book> getBookByTitle(String title){
        try{
            return bookRepository.findByTitle(title);
        }catch(Exception e){
            log.error("Error occurred while fetching books by title: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching books by title", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getBookByAuthor(Author author){
        try{
            return bookRepository.findByAuthor(author);
        }catch(Exception e){
            log.error("Error occurred while fetching books by author: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching books by author", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getBookByTitleAndVersion(String title, Short version){
        try{
            List<Book> filteredBooks = new LinkedList<>();
            List<Book> titledBooks = getBookByTitle(title);
            for(Book titledBook : titledBooks){
                if(version.equals(titledBook.getBookVersion())){
                    filteredBooks.add(titledBook);
                }
            }
            return filteredBooks;
        }catch(Exception e){
            log.error("Error occurred while fetching books by title and version: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching books by title and version", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getBookInRange(LocalDate from,LocalDate to){
        try{
            return bookRepository.findBooksWrittenInDateRange(from,to);
        }catch(Exception e){
            log.error("Error occurred while fetching books In range: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching books In Range", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        try{
            return bookRepository.findAll();
        }catch(Exception e){
            log.error("Error occurred while fetching all books: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching all books", e);
        }
    }

    @Transactional
    public void removeBook(Book book){
        try{
            bookRepository.delete(book);
        }catch (Exception e){
            log.error("Error occurred while removing book: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while removing book", e);
        }
    }

    @Transactional
    public void removeBookById(String id){
        try{
            bookRepository.deleteById(id);
        }catch(Exception e){
            log.error("Error occurred while removing book by ID: {}", id, e);
            throw new RuntimeException("Error occurred while removing book by ID: " + id, e);
        }
    }
}
