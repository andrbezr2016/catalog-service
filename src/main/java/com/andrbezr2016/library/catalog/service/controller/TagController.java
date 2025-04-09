package com.andrbezr2016.library.catalog.service.controller;

import com.andrbezr2016.library.catalog.service.dto.TagDto;
import com.andrbezr2016.library.catalog.service.dto.TagFilter;
import com.andrbezr2016.library.catalog.service.dto.TagInput;
import com.andrbezr2016.library.catalog.service.dto.TagUpdate;
import com.andrbezr2016.library.catalog.service.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class TagController {

    private final TagService tagService;

    @QueryMapping
    public Collection<TagDto> getTags(@Argument("tagFilter") TagFilter tagFilter) {
        return tagService.getTags(tagFilter);
    }

    @MutationMapping
    public TagDto addTag(@Argument("tagInput") TagInput tagInput) {
        return tagService.addTag(tagInput);
    }

    @MutationMapping
    public TagDto updateTag(@Argument("id") UUID id, @Argument("tagUpdate") TagUpdate tagUpdate) {
        return tagService.updateTag(id, tagUpdate);
    }
}
