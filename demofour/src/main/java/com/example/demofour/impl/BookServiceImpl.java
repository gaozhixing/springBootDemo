package com.example.demofour.impl;

import com.example.demofour.entity.Book;
import com.example.demofour.iService.BookRepository;
import com.example.demofour.iService.BookService;
import org.apache.lucene.search.SortField;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


	public Book save(Book book) {
        return bookRepository.save(book);
    }
 
    public void delete(Book book) {
        bookRepository.delete(book);
    }
 
    public Optional<Book> findOne(String id) {
        return  bookRepository.findById(id);
    }
 
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
 
    public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Page<Book> findByTitleAll(String title){
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(new TermQueryBuilder("title",title));
        searchQueryBuilder.withQuery(boolQueryBuilder).
                withIndices("mkyong").
                withTypes("books").
                withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC)).
                withPageable(new PageRequest(0,10));
        return elasticsearchTemplate.queryForPage(searchQueryBuilder.build(),Book.class);
    }
    /*public List<Book> findByTitleAll(String title){
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(new TermQueryBuilder("title",title));
        searchQueryBuilder.withQuery(boolQueryBuilder);
        return elasticsearchTemplate.queryForList(searchQueryBuilder.build(),Book.class);
    }*/
}
