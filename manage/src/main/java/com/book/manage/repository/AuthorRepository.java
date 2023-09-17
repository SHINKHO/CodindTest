package com.book.manage.repository;

import com.book.manage.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository

public interface AuthorRepository extends JpaRepository<Author, Long> {

    public List<Author> findAll();

    public Optional<Author> findById(Long id);

    public List<Author> findByFirstName(String firstName);

    public List<Author> findByLastName(String lastName);

    public Author findByFirstNameAndLastName(String firstName,String lastName);

    public Author save(Author author);

    public void delete(Author author);

    public void deleteById(Long id);
}