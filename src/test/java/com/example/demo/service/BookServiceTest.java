package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.exception.NoBooksFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    BookRepository bookRepositoryMock;

    @BeforeEach
    void setUp() {
        bookRepositoryMock = Mockito.mock(BookRepository.class);
    }

    BookService createUnderTest() {
        return new BookService(bookRepositoryMock);
    }

    @ParameterizedTest
    @MethodSource(value= "getBooksByTitleParams")
    void getBooksByTitle(List<Book> booksInDatabase, List<Book> expectedBooks) throws NoBooksFoundException {
        // given
        Mockito.when(bookRepositoryMock.findAll()).thenReturn(booksInDatabase);
        BookService underTest = createUnderTest();

        //when
        List<Book> actualList = underTest.getBooksByTitle("Harry Potter");

        //then
        Assertions.assertThat(actualList).containsExactlyElementsOf(expectedBooks);
        Mockito.verify(bookRepositoryMock).findAll();
    }

    static Stream<Arguments> getBooksByTitleParams() {
        return Stream.of(
                Arguments.of(Collections.emptyList(),
                        Collections.emptyList()),
                Arguments.of(Arrays.asList(new Book(1L,"Harry Potter", "Author", "Content", 10)),
                        Arrays.asList(new Book(1L,"Harry Potter", "Author", "Content", 13))),
                Arguments.of(Arrays.asList(new Book(1L,"Harry Blotter", "Author", "Content", 15)),
                        Collections.emptyList())
        );
    }


}