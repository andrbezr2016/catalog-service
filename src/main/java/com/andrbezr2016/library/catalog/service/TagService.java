package com.andrbezr2016.library.catalog.service;

import com.andrbezr2016.library.catalog.dto.TagDto;
import com.andrbezr2016.library.catalog.dto.TagInput;
import com.andrbezr2016.library.catalog.dto.TagUpdate;
import com.andrbezr2016.library.catalog.entity.Tag;
import com.andrbezr2016.library.catalog.mapper.TagMapper;
import com.andrbezr2016.library.catalog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Transactional
    public TagDto addTag(TagInput tagInput) {
        if (tagInput == null) {
            throw new RuntimeException("TagInput is empty!");
        }

        Tag input = tagMapper.toEntity(tagInput);
        input = tagRepository.save(input);
        return tagMapper.toDto(input);
    }

    @Transactional
    public TagDto updateTag(UUID id, TagUpdate tagUpdate) {
        if (tagUpdate == null) {
            throw new RuntimeException("TagUpdate is empty!");
        }

        Tag update = tagMapper.toEntity(tagUpdate);
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag != null) {
            if (update.getName() != null) {
                tag.setName(update.getName());
            }
            tag = tagRepository.save(tag);
            return tagMapper.toDto(tag);
        } else {
            throw new RuntimeException("Tag for update doesn't exist!");
        }
    }
}
