package com.example.demo.controller;

import com.example.demo.domain.Book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBooksByTitle() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/booksByTitle")
                .param("title", "Harry Potter"))
                .andDo(print())
                .andExpect(content().string(""))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new Book(1L, "Harry Potter", "Author", "Content", 10))))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/booksByTitle")
                .param("title", "Harry Potter"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":1,\"title\":\"Harry Potter\",\"author\":\"Author\",\"content\":\"Content\"}]"))
                .andExpect(status().isOk());

    }

}
