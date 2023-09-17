package com.book.manage.controller;

import com.book.manage.domain.Author;
import com.book.manage.domain.TransactionRent;
import com.book.manage.repository.AuthorRepository;
import com.book.manage.repository.TransactionRentRepository;
import com.book.manage.service.TransactionReturnService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RestfulAuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TransactionRentRepository rentRepository;

    @Test
    public void testGetAllAuthors() throws Exception {
        mockMvc.perform(get("/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)); // Add appropriate assertions
    }

    @Test
    public void testGetAuthorById() throws Exception {
        // Create an Author object with known data and add it to the database
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        // Save the author to the database
        author = authorRepository.save(author);

        // Perform the get operation using the author's ID
        mockMvc.perform(get("/authors/{id}", author.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(author.getId())) // Expect the actual ID
                .andExpect(jsonPath("$.firstName").value(author.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(author.getLastName()));
    }

    @Test
    public void testAddAuthor() throws Exception {
        // Create an Author object and convert it to JSON
        Author author = new Author();
        author.setFirstName("Jane");
        author.setLastName("Smith");

        // Set the ID to null because it will be auto-generated
        author.setId(null);

        String authorJson = objectMapper.writeValueAsString(author);

        // Add test for adding an author
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(status().isCreated());

        // Add assertions to check if the author was added successfully
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        // Create an Author object and convert it to JSON
        Author author = new Author();

        // Set the ID and other properties
        author.setId(1L);
        author.setFirstName("UpdatedFirstName");
        author.setLastName("UpdatedLastName");

        String authorJson = objectMapper.writeValueAsString(author);

        // Add test for updating an author
        mockMvc.perform(put("/authors/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(status().isOk());

        // Add assertions to check if the author was updated successfully
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        // Create an Author object and add it to the database
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        // Save the author to the database
        author = authorRepository.save(author);

        // Perform the delete operation using the author's ID
        mockMvc.perform(delete("/authors/{id}", author.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify that the author has been deleted by trying to retrieve it again
        mockMvc.perform(get("/authors/{id}", author.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


//    @Test
//    public void testAddRent() throws Exception {
//        // Create a TransactionRent object and convert it to JSON
//        TransactionRent rent = new TransactionRent();
//
//        // Set the necessary properties for the rent object
//
//        String rentJson = objectMapper.writeValueAsString(rent);
//
//        // Add test for adding a rent
//        mockMvc.perform(post("/rents")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(rentJson))
//                .andExpect(status().isCreated());
//
//        // Add assertions to check if the rent was added successfully
//    }
//
//    @Test
//    public void testGetAllRents() throws Exception {
//        // Add test for getting all rents
//        mockMvc.perform(get("/rents")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)); // Add appropriate assertions
//    }
//
//    public void testGetRentById() throws Exception {
//        // Create a TransactionRent object with known data and add it to the database
//        TransactionRent rent = new TransactionRent();
//        rent.setMember("Some Property 1"); // Replace with the actual property name
//        rent.setBook("Some Property 2"); // Replace with the actual property name
//
//        // Save the rent to the database
//        rent = rentRepository.save(rent);
//
//        // Perform the get operation using the rent's ID
//        mockMvc.perform(get("/rents/{id}", rent.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(rent.getId()))
//                .andExpect(jsonPath("$.property1").value(rent.getProperty1()))
//                .andExpect(jsonPath("$.property2").value(rent.getProperty2()));
//    }
//
//    @Test
//    public void testRemoveRent() throws Exception {
//        // Create a TransactionRent object and add it to the database
//        TransactionRent rent = new TransactionRent();
//        // Set the necessary properties for the rent object
//
//        // Save the rent to the database
//        rent = rentRepository.save(rent);
//
//        // Perform the delete operation using the rent's ID
//        mockMvc.perform(delete("/rents/{id}", rent.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//
//        // Verify that the rent has been deleted by trying to retrieve it again
//        mockMvc.perform(get("/rents/{id}", rent.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

}
