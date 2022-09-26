package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.exception.BookRepository;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    BookService bookServiceMock;

    @MockBean
    BookRepository bookRepositoryMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBooksByTitle() throws Exception {
        when(bookServiceMock.getBooksByTitle("Harry Potter"))
                .thenReturn(Arrays.asList(new Book(1L, "Harry Potter", "Author", "Contents", 10)));

        mockMvc.perform(get("/booksByTitle")
                .param("title", "Harry Potter"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getBooksByTitle_emptyDb() throws Exception {
        when(bookServiceMock.getBooksByTitle("Harry Potter"))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/booksByTitle")
                .param("title", "Harry Potter"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}