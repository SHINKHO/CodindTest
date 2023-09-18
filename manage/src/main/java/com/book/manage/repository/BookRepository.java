package com.book.manage.repository;

import com.book.manage.domain.Author;
import com.book.manage.domain.Book;
import com.book.manage.domain.KdcCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    public List<Book> findByTitle(String title);

    public List<Book> findByAuthor(Author author);

    public List<Book> findByKdcCode(KdcCode kdcCode);

    public List<Book> findByIsbnIn(List<String> isbnsList);

    public Integer countByAuthor(Author author);

    public boolean existsByIsbn(String isbn);

    public Optional<Book> findFirstByTitle(String title);

    public List<Book> findByTitleAndVersion(String title, Short version);

    List<Book> findBooksWrittenInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
