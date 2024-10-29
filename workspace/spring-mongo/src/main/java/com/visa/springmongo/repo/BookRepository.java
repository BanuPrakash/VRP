package com.visa.springmongo.repo;


import com.visa.springmongo.document.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Integer> {
    // get all books by author name
    @Query("{'author.name': ?0}")
    public List<Book> findByAuthorName(String name);


}