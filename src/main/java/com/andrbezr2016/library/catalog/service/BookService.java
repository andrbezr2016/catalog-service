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
        if (bookFilter == null) {
            throw new RuntimeException("BookFilter is null!");
        }

        Book filter = bookMapper.toEntity(bookFilter);
        Collection<Specification<Book>> specs = new ArrayList<>();
        if (filter.getId() != null) {
            specs.add(byId(filter.getId()));
        }
        if (filter.getTitle() != null) {
            specs.add(byTitle(filter.getTitle()));
        }
        if (filter.getDescription() != null) {
            specs.add(byDescription(filter.getDescription()));
        }
        if (filter.getAuthor() != null) {
            specs.add(byAuthor(filter.getAuthor()));
        }
        if (filter.getPublisher() != null) {
            specs.add(byPublisher(filter.getPublisher()));
        }
        if (filter.getYearPublished() != null) {
            specs.add(byYearPublished(filter.getYearPublished()));
        }
        if (filter.getIsbn() != null) {
            specs.add(byIsbn(filter.getIsbn()));
        }
        if (filter.getPages() != null) {
            specs.add(byPages(filter.getPages()));
        }
        if (filter.getTags() != null) {
            specs.add(byTags(filter.getTags()));
        }

        if (!specs.isEmpty()) {
            return bookMapper.toDtoCollection(bookRepository.findAll(Specification.allOf(specs)));
        } else {
            return bookMapper.toDtoCollection(bookRepository.findAll());
        }
    }

    private static Specification<Book> byId(UUID id) {
        return (record, cq, cb) -> cb.equal(record.get("id"), id);
    }

    private static Specification<Book> byTitle(String title) {
        return (record, cq, cb) -> cb.like(record.get("title"), "%" + title + "%");
    }

    private static Specification<Book> byDescription(String description) {
        return (record, cq, cb) -> cb.like(record.get("description"), "%" + description + "%");
    }

    private static Specification<Book> byAuthor(String author) {
        return (record, cq, cb) -> cb.like(record.get("author"), "%" + author + "%");
    }

    private static Specification<Book> byPublisher(String publisher) {
        return (record, cq, cb) -> cb.like(record.get("publisher"), "%" + publisher + "%");
    }

    private static Specification<Book> byYearPublished(Integer yearPublished) {
        return (record, cq, cb) -> cb.equal(record.get("yearPublished"), yearPublished);
    }

    private static Specification<Book> byIsbn(String isbn) {
        return (record, cq, cb) -> cb.like(record.get("isbn"), "%" + isbn + "%");
    }

    private static Specification<Book> byPages(Integer pages) {
        return (record, cq, cb) -> cb.equal(record.get("pages"), pages);
    }

    private static Specification<Book> byTags(List<Tag> tags) {
        Collection<Specification<Book>> specs = new ArrayList<>();
        for (Tag tag : tags) {
            Specification<Book> spec = (record, cq, cb) -> {
                Objects.requireNonNull(cq).distinct(true);
                return cb.equal(record.get("tags").get("name"), tag.getName());
            };
            specs.add(spec);
        }
        return Specification.anyOf(specs);
    }

    @Transactional
    public BookDto addBook(BookInput bookInput) {
        if (bookInput == null) {
            throw new RuntimeException("BookInput is null!");
        }

        Book input = bookMapper.toEntity(bookInput);
        List<Tag> tags = mergeTags(input.getTags());
        input.setTags(tags);
        input = bookRepository.save(input);
        return bookMapper.toDto(input);
    }

    @Transactional
    public BookDto updateBook(UUID id, BookUpdate bookUpdate) {
        if (bookUpdate == null) {
            throw new RuntimeException("BookUpdate is null!");
        }

        Book update = bookMapper.toEntity(bookUpdate);
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            List<Tag> tags = mergeTags(update.getTags());
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

    private List<Tag> mergeTags(List<Tag> tags) {
        if (tags != null) {
            List<String> names = tags.stream().map(Tag::getName).toList();
            Map<String, Tag> tagMap = tagRepository.findAllByNameInUsingMap(names);
            return tags.stream().flatMap(newTag -> Stream.of(tagMap.getOrDefault(newTag.getName(), newTag))).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
