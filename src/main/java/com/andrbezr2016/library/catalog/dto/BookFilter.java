package com.andrbezr2016.library.catalog.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class BookFilter {

    private UUID id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private Integer yearPublished;
    private String isbn;
    private Integer pages;
    private List<TagFilter> tags;
}
