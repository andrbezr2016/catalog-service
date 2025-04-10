package com.andrbezr2016.library.catalog.controller;

import com.andrbezr2016.library.catalog.dto.BookDto;
import com.andrbezr2016.library.catalog.dto.BookFilter;
import com.andrbezr2016.library.catalog.dto.BookInput;
import com.andrbezr2016.library.catalog.dto.BookUpdate;
import com.andrbezr2016.library.catalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@GraphQlTest(BookController.class)
class BookControllerTest {

    @Autowired
    private GraphQlTester tester;
    @MockitoBean
    private BookService bookService;

    @Test
    public void getBooks() {
        List<BookDto> expectedBookDtoList = List.of(BookDto.builder().id(UUID.fromString("b9ee6225-1bc3-4524-bac5-69e4193658b5")).title("Test Title").build());
        doReturn(expectedBookDtoList).when(bookService).getBooks(eq(BookFilter.builder().build()));

        List<BookDto> bookDtoList = tester.documentName("getAllBooks").execute().path("data.getBooks").entityList(BookDto.class).get();

        assertNotNull(bookDtoList);
        assertFalse(bookDtoList.isEmpty());
        assertEquals(expectedBookDtoList.getFirst(), bookDtoList.getFirst());
    }

    @Test
    public void addBook() {
        BookDto expectedBookDto = BookDto.builder().id(UUID.fromString("09a7e0bf-e8e9-429b-88aa-e6bcc6b910a9")).title("Test Title").build();
        doReturn(expectedBookDto).when(bookService).addBook(eq(BookInput.builder().title("Test Title").build()));

        BookDto bookDto = tester.documentName("addBook").execute().path("data.addBook").entity(BookDto.class).get();

        assertNotNull(bookDto);
        assertEquals(expectedBookDto, bookDto);
    }

    @Test
    public void updateBook() {
        BookDto expectedBookDto = BookDto.builder().id(UUID.fromString("862f7c7b-b152-4bd3-a537-2f21c3067759")).title("Test Title").build();
        doReturn(expectedBookDto).when(bookService).updateBook(eq(UUID.fromString("862f7c7b-b152-4bd3-a537-2f21c3067759")), eq(BookUpdate.builder().title("Test Title").build()));

        BookDto bookDto = tester.documentName("updateBook").execute().path("data.updateBook").entity(BookDto.class).get();

        assertNotNull(bookDto);
        assertEquals(expectedBookDto, bookDto);
    }

    @Test
    public void deleteBook() {
        BookDto expectedBookDto = BookDto.builder().id(UUID.fromString("2f2b1569-e02c-4cce-9c0f-5643eefdf595")).title("Test Title").build();
        doReturn(expectedBookDto).when(bookService).deleteBook(eq(UUID.fromString("2f2b1569-e02c-4cce-9c0f-5643eefdf595")));

        BookDto bookDto = tester.documentName("deleteBook").execute().path("data.deleteBook").entity(BookDto.class).get();

        assertNotNull(bookDto);
        assertEquals(expectedBookDto, bookDto);
    }
}