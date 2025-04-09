package com.andrbezr2016.library.catalog.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class BookDto {

    private UUID id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private Integer yearPublished;
    private String isbn;
    private Integer pages;
    private Set<TagDto> tags;
}
