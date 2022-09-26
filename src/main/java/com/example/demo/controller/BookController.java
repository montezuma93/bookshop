package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.exception.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.exception.NoBooksFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class BookController {

    BookRepository bookRepository;
    BookService bookService;

    @Autowired
    BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public void saveBooks(
            @RequestBody List<Book> books) {
        bookRepository.saveAll(books);
    }

    @GetMapping("/booksByTitle")
    public ResponseEntity<List<Book>> getBooksByTitles(@RequestParam String title) throws NoBooksFoundException {
        List<Book> books = bookService.getBooksByTitle(title);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);

        }
    }

    @GetMapping("/booksByPrice")
    //ResponseEntity: gibt zurück body und status code
    public ResponseEntity<List<Book>> getBooksByPrice(@RequestParam int price) {
        List<Book> books = bookService.getBooksByPrice(price, price);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/booksByPrices") // Einstiegspunkt in der URL
    public ResponseEntity<List<Book>> getBooksByPrices(@RequestParam int startPrice, @RequestParam int endPrice) {
        List<Book> books = bookService.getBooksByPrice(startPrice, endPrice);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(@PathParam("name") String name) {
        return "Hello World " + name;
    }

}
