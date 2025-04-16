package com.andrbezr2016.library.catalog.service;

import com.andrbezr2016.library.catalog.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void getBooksByIds() {
        List<UUID> ids = List.of(UUID.fromString("5d643b16-ebad-4b1b-b586-2ee92b070d00"), UUID.fromString("66c25e62-7363-4fe7-8f05-91791db65789"));
        Collection<BookDto> bookDtoCollection = bookService.getBooksByIds(ids);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooksExcludingIds() {
        List<UUID> ids = List.of(UUID.fromString("5d643b16-ebad-4b1b-b586-2ee92b070d00"), UUID.fromString("66c25e62-7363-4fe7-8f05-91791db65789"));
        Collection<BookDto> bookDtoCollection = bookService.getBooksExcludingIds(ids);

        assertNotNull(bookDtoCollection);
        assertTrue(bookDtoCollection.size() >= 4);
        assertFalse(bookDtoCollection.stream().map(BookDto::getId).toList().contains(ids.getFirst()));
        assertFalse(bookDtoCollection.stream().map(BookDto::getId).toList().contains(ids.getLast()));
    }

    @Test
    void getBooks_All() {
        BookFilter bookFilter = BookFilter.builder().build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertTrue(bookDtoCollection.size() >= 6);
    }

    @Test
    void getBooks_Null() {
        assertThrowsExactly(RuntimeException.class, () -> bookService.getBooks(null));
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
        BookFilter bookFilter = BookFilter.builder().tags(List.of(TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags2() {
        BookFilter bookFilter = BookFilter.builder().tags(List.of(TagFilter.builder().name("Tag 5").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags3() {
        BookFilter bookFilter = BookFilter.builder().tags(List.of(TagFilter.builder().name("Tag 5").build(), TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags4() {
        BookFilter bookFilter = BookFilter.builder().tags(List.of(TagFilter.builder().name("Tag 10").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(0, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByTags5() {
        BookFilter bookFilter = BookFilter.builder().tags(List.of(TagFilter.builder().name("Tag 10").build(), TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(2, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByIsbnAndTags() {
        BookFilter bookFilter = BookFilter.builder().isbn("5-55-xxxxxx-x").tags(List.of(TagFilter.builder().name("Tag 3").build())).build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void getBooks_ByAuthorAndDescription() {
        BookFilter bookFilter = BookFilter.builder().author("Ivan I").description("D Desc").build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(1, bookDtoCollection.size());
    }

    @Test
    void getBooks_BatchJob() {
        BookFilter bookFilter = BookFilter.builder().isbn("xxx-xxx-xxx").build();

        Collection<BookDto> bookDtoCollection = bookService.getBooks(bookFilter);

        assertNotNull(bookDtoCollection);
        assertEquals(3, bookDtoCollection.size());
    }

    @Test
    void addBook() {
        BookInput bookInput = BookInput.builder().title("G Book").build();

        BookDto bookDto = bookService.addBook(bookInput);

        assertNotNull(bookDto);
        assertEquals(bookInput.getTitle(), bookDto.getTitle());
    }

    @Test
    void addBook_WithNull() {
        assertThrowsExactly(RuntimeException.class, () -> bookService.addBook(null));
    }

    @Test
    void updateBook() {
        UUID id = UUID.fromString("66c25e62-7363-4fe7-8f05-91791db65789");
        BookUpdate bookUpdate = BookUpdate.builder().title("A Book Updated").build();

        BookDto bookDto = bookService.updateBook(id, bookUpdate);

        assertNotNull(bookDto);
        assertEquals(id, bookDto.getId());
        assertEquals(bookUpdate.getTitle(), bookDto.getTitle());
    }

    @Test
    void updateBook_WithNull() {
        UUID id = UUID.fromString("66c25e62-7363-4fe7-8f05-91791db65789");

        assertThrowsExactly(RuntimeException.class, () -> bookService.updateBook(id, null));
    }

    @Test
    void updateBook_WithWrongId() {
        UUID id = UUID.randomUUID();
        List<TagUpdate> tagUpdateList = List.of(
                TagUpdate.builder().name("Tag 1").build(),
                TagUpdate.builder().name("Tag 13").build(),
                TagUpdate.builder().name("Tag 2").build()
        );
        BookUpdate bookUpdate = BookUpdate.builder().yearPublished(2003).tags(tagUpdateList).build();

        assertThrowsExactly(RuntimeException.class, () -> bookService.updateBook(id, bookUpdate));
    }

    @Test
    void updateBook_WithTags() {
        UUID id = UUID.fromString("66c25e62-7363-4fe7-8f05-91791db65789");
        List<TagUpdate> tagUpdateList = List.of(
                TagUpdate.builder().name("Tag 1").build(),
                TagUpdate.builder().name("Tag 13").build(),
                TagUpdate.builder().name("Tag 2").build()
        );
        BookUpdate bookUpdate = BookUpdate.builder().yearPublished(2003).tags(tagUpdateList).build();

        BookDto bookDto = bookService.updateBook(id, bookUpdate);

        assertNotNull(bookDto);
        assertEquals(id, bookDto.getId());
        assertEquals(bookUpdate.getYearPublished(), bookDto.getYearPublished());
        assertEquals(bookUpdate.getTags().stream().map(TagUpdate::getName).collect(Collectors.toSet()), bookDto.getTags().stream().map(TagDto::getName).collect(Collectors.toSet()));
    }

    @Test
    void deleteBook() {
        UUID id = UUID.fromString("450436a2-c9de-4ade-8a2c-5230313de3e4");

        BookDto bookDto = bookService.deleteBook(id);

        assertNotNull(bookDto);
        assertEquals(id, bookDto.getId());
    }

    @Test
    void deleteBook_WithWrongId() {
        UUID id = UUID.randomUUID();

        assertThrowsExactly(RuntimeException.class, () -> bookService.deleteBook(id));
    }
}