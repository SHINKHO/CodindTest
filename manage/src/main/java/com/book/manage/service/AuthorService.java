package com.book.manage.service;

import com.book.manage.domain.Author;
import com.book.manage.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public void addAuthor(Author author){
        try {
            authorRepository.save(author);
        }catch (Exception e){
            log.error("Error occurred while adding author: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while adding author", e);
        }
    }

    @Transactional
    public void modifyAuthor(Long id,String firstName,String lastName){
        try {
            Optional<Author> authorOpt = authorRepository.findById(id);
            Author author =authorOpt.orElse(null);
            author.setFirstName(firstName);
            author.setLastName(lastName);
        }catch(Exception e){
            log.error("Error occurred while modifying author: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while modifying author", e);
        }
    }

    @Transactional
    public Author getAuthorById(Long id){
        try{
            Optional<Author> memberOpt = authorRepository.findById(id);
            return memberOpt.orElse(null);
        }catch (Exception e){
            log.error("Error occurred while fetching author by ID: {}", id, e);
            throw new RuntimeException("Error occurred while fetching author by ID: " + id, e);
        }
    }

    @Transactional
    public List<Author> getAuthorByFirstName(String firstName){
        List<Author> authors = null;

        try{
            authors = authorRepository.findByFirstName(firstName);
        }catch(Exception e){
            log.error("Error occurred while fetching authors by first name: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching authors by first name", e);
        }

        return authors;
    }

    @Transactional
    public List<Author> getAuthorByLastName(String lastName){
        List<Author> authors = null;

        try{
            authors = authorRepository.findByLastName(lastName);
        }catch(Exception e){
            log.error("Error occurred while fetching authors by last name: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching authors by last name", e);
        }

        return authors;
    }

    @Transactional
    public List<Author> getAuthorContains(String name){
        try{
            List<Author> authors = new LinkedList<>();
            List<Author> unfilteredAuthors = authorRepository.findAll();
            for(Author author : unfilteredAuthors){
                if(author.getFirstName().contains(name) || author.getLastName().contains(name)){
                    authors.add(author);
                }
            }
            return authors;
        }catch(Exception e){
            log.error("Error occurred while fetching authors containing name: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching authors containing name", e);
        }
    }

    @Transactional
    public List<Author> getAllAuthor(){
        try {
            return authorRepository.findAll();
        }catch (Exception e){
            log.error("Error occurred while fetching all authors: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching all authors", e);
        }
    }

    @Transactional
    public void removeAuthor(Author author){
        try{
            authorRepository.delete(author);
        }catch (Exception e){
            log.error("Error occurred while removing author: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while removing author", e);
        }
    }

    @Transactional
    public void removeAuthorById(Long id){
        try {
            authorRepository.deleteById(id);
        }catch (Exception e){
            log.error("Error occurred while removing author by ID: {}", id, e);
            throw new RuntimeException("Error occurred while removing author by ID: " + id, e);
        }
    }
}
