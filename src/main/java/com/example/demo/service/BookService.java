package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.exception.NoBooksFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    public BookRepository bookRepository;

    @Autowired
    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

   public List<Book> getBooksByTitle(String title) throws NoBooksFoundException {
       List<Book> allBooks = bookRepository.findAll();
       List<Book> foundTitles = new ArrayList<>();
       for (Book book : allBooks) {
           if (Objects.equals(book.getTitle(), title)) {
               foundTitles.add(book);
           }
       }

       /*
       if(foundTitles.isEmpty()) {
           throw new NoBooksFoundException("No books found");
       }
        */
       return foundTitles;
   }
    public List<Book> getBooksByPrice(int price) {
        //gibt alle bücher aus der datenbank züruck
        List<Book> allBooks = bookRepository.findAll();
        //erstellt eine neue lehre liste, in der alle bucher abgespeichert werden mit dem gesuchten preis
        List<Book> foundBooksByPrices = new ArrayList<>();
        //holt ein buch nach dem anderen aus der Liste allBooks
        for (Book book : allBooks) {
            //book.getPrice() == price
            //wenn der preis des buches gleich ist wie der gesucher preis, dann gehe in die if bedienung
            if(Objects.equals(book.getPrice(), price)) {
                //speichert das buch in die Liste foundBooksByPrices
                foundBooksByPrices.add(book);
            }
        }
        return foundBooksByPrices;
    }
}
