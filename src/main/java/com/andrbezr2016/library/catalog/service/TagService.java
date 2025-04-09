package com.andrbezr2016.library.catalog.service;

import com.andrbezr2016.library.catalog.dto.TagDto;
import com.andrbezr2016.library.catalog.dto.TagFilter;
import com.andrbezr2016.library.catalog.dto.TagInput;
import com.andrbezr2016.library.catalog.dto.TagUpdate;
import com.andrbezr2016.library.catalog.mapper.TagMapper;
import com.andrbezr2016.library.catalog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public Collection<TagDto> getTags(TagFilter tagFilter) {
        return null;
    }

    public TagDto addTag(TagInput tagInput) {
        return null;
    }

    public TagDto updateTag(UUID id, TagUpdate tagUpdate) {
        return null;
    }
}
