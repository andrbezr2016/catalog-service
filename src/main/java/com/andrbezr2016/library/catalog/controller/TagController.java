package com.andrbezr2016.library.catalog.controller;

import com.andrbezr2016.library.catalog.dto.TagDto;
import com.andrbezr2016.library.catalog.dto.TagInput;
import com.andrbezr2016.library.catalog.dto.TagUpdate;
import com.andrbezr2016.library.catalog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class TagController {

    private final TagService tagService;

    @MutationMapping
    public TagDto addTag(@Argument("tagInput") TagInput tagInput) {
        return tagService.addTag(tagInput);
    }

    @MutationMapping
    public TagDto updateTag(@Argument("id") UUID id, @Argument("tagUpdate") TagUpdate tagUpdate) {
        return tagService.updateTag(id, tagUpdate);
    }
}
