package com.andrbezr2016.library.catalog.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class BookInput {

    private String title;
    private String description;
    private String author;
    private String publisher;
    private Integer yearPublished;
    private String isbn;
    private Integer pages;
    private List<TagInput> tags;
}
