package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;

   public List<Book> getBooksByTitle(String title){
     List<Book> allBooks = bookRepository.findAll();
       List<Book> foundTitles= new ArrayList<>();
       for(Book book: allBooks){
         if(Objects.equals(book.getTitle(), title)){
             foundTitles.add(book);
         }
       }
      return foundTitles;
   }
}
