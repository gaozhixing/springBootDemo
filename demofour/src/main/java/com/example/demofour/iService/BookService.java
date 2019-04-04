package com.example.demofour.iService;

import com.example.demofour.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {
 
    Book save(Book book);
 
    void delete(Book book);

    Optional<Book> findOne(String id);
 
    Iterable<Book> findAll();
 
    Page<Book> findByAuthor(String author, PageRequest pageRequest);
 
    List<Book> findByTitle(String title);

    Page<Book> findByTitleAll(String title);

    //List<Book> findByTitleAll(String title);
}
