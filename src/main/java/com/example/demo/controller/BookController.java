package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @PostMapping("/books")
    public void saveBook(
            @RequestBody Book book) {
        bookRepository.save(book);
    }

    @GetMapping("/booksByTitle")
    public List<Book> getBooksByTitles(@RequestParam String title){
       return bookService.getBooksByTitle(title);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/hello")
    public String hello(@PathParam("name") String name) {
        return "Hello World " + name;
    }


}
