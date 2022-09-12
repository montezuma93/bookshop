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
       List<Book> foundTitles= new ArrayList<>();
       for(Book book: allBooks){
         if(Objects.equals(book.getTitle(), title)){
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
}
