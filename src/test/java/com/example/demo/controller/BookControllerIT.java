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

import java.util.ArrayList;
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
    private MockMvc mockMvc; // Hilft uns Integrationstest zu schreiben, gibt uns die Möglichkeit ein Request abzusenden

    @Test
    void getBooksByTitle() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(); // Kann Objekte in ein JSON-Fomat umwandeln
        mockMvc.perform(get("/books")) // Methode perform führt den Request get aus
                .andDo(print())
                .andExpect(content().string("[]")) // Rückgabe der Werte, erwartet eine leere Liste
                .andExpect(status().isOk()); // HTTP Status 200 (Ok)

        mockMvc.perform(get("/booksByTitle")
                        .param("title", "Harry Potter"))
                .andDo(print())
                .andExpect(content().string(""))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/books")  // Request post fügt Objekte hinzu
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Schickt einen Header, der Inhalt des Headers sagt aus das ein JSON-Objekt gesendet wird
                        .accept(MediaType.APPLICATION_JSON_VALUE) // Egal was zurückkommt hauptsache ein JSON
                        // erstellt einen Body und fügt ihm dem Request hinzu
                        // objektMapper wandlet das Objekt in ein JSON String um
                        .content(objectMapper.writeValueAsString(Arrays.asList(
                                new Book(1L, "Harry Potter", "Author", "Content", 10),
                                new Book(2L, "Harry Potter", "Author", "Content", 12),
                                new Book(3L, "Peter Hase", "Author", "Content", 14),
                                new Book(4L, "Raabe Socke", "Author", "Content", 14),
                                new Book(5L, "Raabe Socke", "Author", "Content", 22)
                        ))))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/booksByTitle")
                        .param("title", "Harry Potter"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":1,\"title\":\"Harry Potter\",\"author\":\"Author\",\"content\":\"Content\",\"price\":10},{\"id\":2,\"title\":\"Harry Potter\",\"author\":\"Author\",\"content\":\"Content\",\"price\":12}]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/booksByPrice")
                        .param("price", "14"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":3,\"title\":\"Peter Hase\",\"author\":\"Author\",\"content\":\"Content\",\"price\":14},{\"id\":4,\"title\":\"Raabe Socke\",\"author\":\"Author\",\"content\":\"Content\",\"price\":14}]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/booksByPrices")
                        .param("startPrice", "10").param("endPrice", "14"))
                .andDo(print())
                .andExpect(content().string("[" + "{\"id\":1,\"title\":\"Harry Potter\",\"author\":\"Author\",\"content\":\"Content\",\"price\":10}," +
                        "{\"id\":2,\"title\":\"Harry Potter\",\"author\":\"Author\",\"content\":\"Content\",\"price\":12}," +
                        "{\"id\":3,\"title\":\"Peter Hase\",\"author\":\"Author\",\"content\":\"Content\",\"price\":14}," +
                        "{\"id\":4,\"title\":\"Raabe Socke\",\"author\":\"Author\",\"content\":\"Content\",\"price\":14}]"))
                .andExpect(status().isOk());

    }

}
