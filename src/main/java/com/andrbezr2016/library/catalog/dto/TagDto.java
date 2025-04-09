package com.andrbezr2016.library.catalog.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class TagDto {

    private UUID id;
    private String name;
}
