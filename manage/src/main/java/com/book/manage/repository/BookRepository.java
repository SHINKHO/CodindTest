package com.book.manage.repository;

import com.book.manage.domain.Author;
import com.book.manage.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findAll();

    public Optional<Book> findById(Long isbn);

    public List<Book> findByTitle(String title);

    public List<Book> findByAuthor(Author author);

    public Book save(Book book);

    public void delete(Book book);

    public void deleteById(Long id);
}
