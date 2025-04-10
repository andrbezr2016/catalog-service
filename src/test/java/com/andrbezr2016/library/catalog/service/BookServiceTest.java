package com.andrbezr2016.library.catalog.service;

import com.andrbezr2016.library.catalog.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void getBooks_All() {
        BookFilter bookFilter = BookFilter.builder().build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertTrue(bookDtoCollection.size() >= 6);
    }

    @Test
    void getBooks_ById() {
        BookFilter bookFilter = BookFilter.builder().id(UUID.fromString("5d643b16-ebad-4b1b-b586-2ee92b070d00")).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByAuthor() {
        BookFilter bookFilter = BookFilter.builder().author("Ivan I").build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByYearPublished() {
        BookFilter bookFilter = BookFilter.builder().yearPublished(40000).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags1() {
        BookFilter bookFilter = BookFilter.builder().tags(Set.of(TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags2() {
        BookFilter bookFilter = BookFilter.builder().tags(Set.of(TagFilter.builder().name("Tag 5").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags3() {
        BookFilter bookFilter = BookFilter.builder().tags(Set.of(TagFilter.builder().name("Tag 5").build(), TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags4() {
        BookFilter bookFilter = BookFilter.builder().tags(Set.of(TagFilter.builder().name("Tag 10").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(0, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags5() {
        BookFilter bookFilter = BookFilter.builder().tags(Set.of(TagFilter.builder().name("Tag 10").build(), TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }


    @Test
    void getBooks_ByAuthorAndDescription() {
        BookFilter bookFilter = BookFilter.builder().author("Ivan I").description("D Desc").build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void addBook() {
        BookInput bookInput = BookInput.builder().title("G Book").build();

        BookDto bookDto = bookService.addBook(bookInput);

        assertNotNull(bookDto);
        assertEquals(bookInput.getTitle(), bookDto.getTitle());
    }

    @Test
    void updateBook() {
        UUID id = UUID.fromString("66c25e62-7363-4fe7-8f05-91791db65789");
        BookUpdate bookUpdate = BookUpdate.builder().title("B Book Updated").build();

        BookDto bookDto = bookService.updateBook(id, bookUpdate);

        assertNotNull(bookDto);
        assertEquals(id, bookDto.getId());
        assertEquals(bookUpdate.getTitle(), bookDto.getTitle());
    }

    @Test
    void deleteBook() {
        UUID id = UUID.fromString("450436a2-c9de-4ade-8a2c-5230313de3e4");

        BookDto bookDto = bookService.deleteBook(id);

        assertNotNull(bookDto);
        assertEquals(id, bookDto.getId());
    }
}