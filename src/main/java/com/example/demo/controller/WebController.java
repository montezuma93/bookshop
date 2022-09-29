package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.exception.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    List<Book> allBooksList = new ArrayList<Book>();

    BookRepository bookRepository;
    BookService bookService;

    @Autowired
    WebController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping("/allBooks")
    public ModelAndView getAllBooks() {

        String viewName = "allBooksList";
        Map<String, Object> model = new HashMap<String, Object>();
        allBooksList.clear();
        allBooksList = bookRepository.findAll();
        model.put("watchlistItems", allBooksList);
        model.put("numberOfBooks", allBooksList.size());
        return new ModelAndView(viewName , model);
    }

    @RequestMapping(value="/do-stuff")
    public String doStuffMethod() {
        System.out.println("Success");
        return "allBooksList";
    }

    @PostMapping("/save-book")
    public ModelAndView saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        String viewName = "allBooksList";
        Map<String, Object> model = new HashMap<String, Object>();
        allBooksList = bookRepository.findAll();
        model.put("watchlistItems", allBooksList);
        model.put("numberOfBooks", allBooksList.size());
        return new ModelAndView(viewName , model);
    }

    @GetMapping("/create-book")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "create-book";
    }
}
