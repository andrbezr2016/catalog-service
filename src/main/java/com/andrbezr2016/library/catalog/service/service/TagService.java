package com.andrbezr2016.library.catalog.service.service;

import com.andrbezr2016.library.catalog.service.dto.TagDto;
import com.andrbezr2016.library.catalog.service.dto.TagFilter;
import com.andrbezr2016.library.catalog.service.dto.TagInput;
import com.andrbezr2016.library.catalog.service.dto.TagUpdate;
import com.andrbezr2016.library.catalog.service.mapper.TagMapper;
import com.andrbezr2016.library.catalog.service.repository.TagRepository;
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
