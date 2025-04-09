package com.andrbezr2016.library.catalog.service;

import com.andrbezr2016.library.catalog.dto.BookDto;
import com.andrbezr2016.library.catalog.dto.BookFilter;
import com.andrbezr2016.library.catalog.dto.BookInput;
import com.andrbezr2016.library.catalog.dto.BookUpdate;
import com.andrbezr2016.library.catalog.entity.Book;
import com.andrbezr2016.library.catalog.entity.Tag;
import com.andrbezr2016.library.catalog.mapper.BookMapper;
import com.andrbezr2016.library.catalog.repository.BookRepository;
import com.andrbezr2016.library.catalog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final BookMapper bookMapper;

    @Transactional
    public Collection<BookDto> getBooks(BookFilter bookFilter) {
        Collection<Specification<Book>> specs = new ArrayList<>();
        if (bookFilter.getId() != null) {
            specs.add(byId(bookFilter.getId()));
        }
        if (bookFilter.getTitle() != null) {
            specs.add(byTitle(bookFilter.getTitle()));
        }
        if (bookFilter.getDescription() != null) {
            specs.add(byDescription(bookFilter.getDescription()));
        }
        if (bookFilter.getAuthor() != null) {
            specs.add(byAuthor(bookFilter.getAuthor()));
        }
        if (bookFilter.getPublisher() != null) {
            specs.add(byPublisher(bookFilter.getPublisher()));
        }
        if (bookFilter.getYearPublished() != null) {
            specs.add(byYearPublished(bookFilter.getYearPublished()));
        }
        if (bookFilter.getIsbn() != null) {
            specs.add(byIsbn(bookFilter.getIsbn()));
        }
        if (bookFilter.getPages() != null) {
            specs.add(byPages(bookFilter.getPages()));
        }

        if (!specs.isEmpty()) {
            return bookMapper.toDtoCollection(bookRepository.findAll(Specification.allOf()));
        } else {
            return bookMapper.toDtoCollection(bookRepository.findAll());
        }
    }

    private Specification<Book> byId(UUID id) {
        return (record, cq, cb) -> cb.equal(record.get("id"), id);
    }

    private Specification<Book> byTitle(String title) {
        return (record, cq, cb) -> cb.equal(record.get("title"), title);
    }

    private Specification<Book> byDescription(String description) {
        return (record, cq, cb) -> cb.equal(record.get("description"), description);
    }

    private Specification<Book> byAuthor(String author) {
        return (record, cq, cb) -> cb.equal(record.get("author"), author);
    }

    private Specification<Book> byPublisher(String publisher) {
        return (record, cq, cb) -> cb.equal(record.get("publisher"), publisher);
    }

    private Specification<Book> byYearPublished(Integer yearPublished) {
        return (record, cq, cb) -> cb.equal(record.get("yearPublished"), yearPublished);
    }

    private Specification<Book> byIsbn(String isbn) {
        return (record, cq, cb) -> cb.equal(record.get("isbn"), isbn);
    }

    private Specification<Book> byPages(Integer pages) {
        return (record, cq, cb) -> cb.equal(record.get("pages"), pages);
    }

    @Transactional
    public BookDto addBook(BookInput bookInput) {
        if (bookInput == null) {
            throw new RuntimeException("BookInput is empty!");
        }

        Book input = bookMapper.toEntity(bookInput);
        Set<Tag> tags = mergeTags(input.getTags());
        input.setTags(tags);
        input = bookRepository.save(input);
        return bookMapper.toDto(input);
    }

    @Transactional
    public BookDto updateBook(UUID id, BookUpdate bookUpdate) {
        if (bookUpdate == null) {
            throw new RuntimeException("BookUpdate is empty!");
        }

        Book update = bookMapper.toEntity(bookUpdate);
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            Set<Tag> tags = mergeTags(update.getTags());
            update.setTags(tags);
            if (update.getTitle() != null) {
                book.setTitle(update.getTitle());
            }
            if (update.getDescription() != null) {
                book.setDescription(update.getDescription());
            }
            if (update.getAuthor() != null) {
                book.setAuthor(update.getAuthor());
            }
            if (update.getPublisher() != null) {
                book.setPublisher(update.getPublisher());
            }
            if (update.getYearPublished() != null) {
                book.setYearPublished(update.getYearPublished());
            }
            if (update.getIsbn() != null) {
                book.setIsbn(update.getIsbn());
            }
            if (update.getPages() != null) {
                book.setPages(update.getPages());
            }
            if (update.getTags() != null) {
                book.setTags(update.getTags());
            }
            book = bookRepository.save(book);
            return bookMapper.toDto(book);
        } else {
            throw new RuntimeException("Book for update doesn't exist!");
        }
    }

    @Transactional
    public BookDto deleteBook(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            bookRepository.delete(book);
            return bookMapper.toDto(book);
        } else {
            throw new RuntimeException("Book for delete doesn't exist!");
        }
    }

    private Set<Tag> mergeTags(Set<Tag> tags) {
        if (tags != null) {
            return tags.stream().flatMap(newTag -> {
                Tag oldTag = tagRepository.findByName(newTag.getName()).orElse(null);
                return Stream.of(Objects.requireNonNullElse(oldTag, newTag));
            }).collect(Collectors.toSet());
        } else {
            return null;
        }
    }
}
