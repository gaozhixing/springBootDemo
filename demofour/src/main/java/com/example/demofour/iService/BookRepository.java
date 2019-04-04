package com.example.demofour.iService;

import com.example.demofour.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookRepository extends ElasticsearchRepository<Book, String>{

	 Page<Book> findByAuthor(String author, Pageable pageable);
	 
	 List<Book> findByTitle(String title);
}
