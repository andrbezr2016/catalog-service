package com.andrbezr2016.library.catalog.service.controller;

import com.andrbezr2016.library.catalog.service.dto.BookDto;
import com.andrbezr2016.library.catalog.service.dto.BookFilter;
import com.andrbezr2016.library.catalog.service.dto.BookInput;
import com.andrbezr2016.library.catalog.service.dto.BookUpdate;
import com.andrbezr2016.library.catalog.service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @QueryMapping
    public Collection<BookDto> getBooks(@Argument("bookFilter") BookFilter bookFilter) {
        return bookService.getBooks(bookFilter);
    }

    @MutationMapping
    public BookDto addBook(@Argument("bookInput") BookInput bookInput) {
        return bookService.addBook(bookInput);
    }

    @MutationMapping
    public BookDto updateBook(@Argument("id") UUID id, @Argument("bookUpdate") BookUpdate bookUpdate) {
        return bookService.updateBook(id, bookUpdate);
    }

    @MutationMapping
    public BookDto deleteBook(@Argument("id") UUID id) {
        return bookService.deleteBook(id);
    }
}
