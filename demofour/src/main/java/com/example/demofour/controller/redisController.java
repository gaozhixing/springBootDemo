package com.example.demofour.controller;


import com.example.demofour.entity.BaseResult;
import com.example.demofour.entity.Book;
import com.example.demofour.entity.PageResult;
import com.example.demofour.entity.TUser;
import com.example.demofour.iService.BookService;
import com.example.demofour.iService.UserService;
import com.example.demofour.util.RedisUtil;
import com.example.demofour.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class redisController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @RequestMapping("/set")
    public Book demo(String id) {
        Book book = new Book();
        book.setAuthor("zhixing");
        book.setId(id);
        book.setReleaseDate("1995-12-10");
        book.setTitle("666999");
        book.setCreatTime(new Date());
        bookService.save(book);
        return bookService.findOne(id).get();
    }

    @RequestMapping("/redisSet")
    public String redisSet(String key ,String value){
        redisUtil.setObject(key,value);
        return redisUtil.getObject(key);
    }

    @RequestMapping("/userSet")
    public TUser userSet(String id){
        TUser user = new TUser();
        user.setId(id);
        user.setAdvise("一生之敌");
        user.setSex("男");
        userService.createUser(user);
        return userService.queryUserById(id);
    }

    /*@RequestMapping("/findByTitle")
    public BaseResult<List<Book>> getByTitle(String title){
        List<Book> books = bookService.findByTitleAll(title);
        return new BaseResult("000","success",books);

    }*/
    @RequestMapping("/findByTitle")
    public PageResult<Book> getByTitle(String title){
        Page<Book> books = bookService.findByTitleAll(title);
        PageResult<Book> pageBooks= new PageResult<>();
        pageBooks.setCount(books.getTotalElements());
        pageBooks.setPageNum(books.getNumber());
        pageBooks.setPageSize(books.getSize());
        pageBooks.setResult(books.getContent());
        pageBooks.setPageCount(books.getTotalPages());
        pageBooks.setResultCode("0000");
        pageBooks.setResultMsg("success");
        return pageBooks;

    }
}
