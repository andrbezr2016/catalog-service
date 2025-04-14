package com.andrbezr2016.library.catalog.controller;

import com.andrbezr2016.library.catalog.dto.BookDto;
import com.andrbezr2016.library.catalog.dto.BookFilter;
import com.andrbezr2016.library.catalog.dto.BookInput;
import com.andrbezr2016.library.catalog.dto.BookUpdate;
import com.andrbezr2016.library.catalog.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @QueryMapping
    public Collection<BookDto> getBooks(@Argument("bookFilter") BookFilter bookFilter) {
        log.info("Find books by filter: {}", bookFilter);
        return bookService.getBooks(bookFilter);
    }

    @MutationMapping
    public BookDto addBook(@Argument("bookInput") BookInput bookInput) {
        log.info("Add book: {}", bookInput);
        return bookService.addBook(bookInput);
    }

    @MutationMapping
    public BookDto updateBook(@Argument("id") UUID id, @Argument("bookUpdate") BookUpdate bookUpdate) {
        log.info("Update book {} by id: {} ", bookUpdate, id);
        return bookService.updateBook(id, bookUpdate);
    }

    @MutationMapping
    public BookDto deleteBook(@Argument("id") UUID id) {
        log.info("Delete book by id: {}", id);
        return bookService.deleteBook(id);
    }
}
