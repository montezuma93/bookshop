package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.exception.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    BookRepository bookRepository;
    BookService bookService;

    @Autowired
    WebController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping("/allBooks")
    public ModelAndView getAllBooks() {
        // Die Html Seite die gerendert werden soll, wird im ModelAndView Object als "View" zurückgegeben.
        String viewName = "allBooks";

        // Erstelle eine Key-Value Map. Die Keys und Values können im Template verwendet werden.
        Map<String, Object> model = new HashMap<String, Object>();

        // Hole alle Bücher aus der Datenbank und speichere sie unter dem Key "bookList" in der Model Map
        List<Book> allBooks = bookRepository.findAll();
        model.put("bookList", allBooks);

        model.put("amountOfBooks", allBooks.size());
        model.put("book",new Book());

        return new ModelAndView(viewName , model);
    }
    @PostMapping("/save-book")
    //Erhalte Buch als Parameter von unserem Template, bzw. unseres th:object="${book}"
    public ModelAndView saveBook(@ModelAttribute Book book) {
        // Speichere das Buch
        bookRepository.save(book);

        // Was sollen wir anzeigen? Welche Html Seite, bzw. welches Template -> allBooks.html
        String viewName = "allBooks";

        // Wir benötigen wieder eine Model Map
        Map<String, Object> model = new HashMap<String, Object>();
        //Befülle die Map
        List<Book> allBooks = bookRepository.findAll();
        model.put("bookList", allBooks);
        model.put("amountOfBooks", allBooks.size());
        model.put("book",new Book());
        return new ModelAndView(viewName, model);
    }

}
