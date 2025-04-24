package com.andrbezr2016.library.catalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "year_published")
    private Integer yearPublished;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "pages")
    private Integer pages;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_tags",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
}
