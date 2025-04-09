package com.andrbezr2016.library.catalog.service.service;

import com.andrbezr2016.library.catalog.service.mapper.TagMapper;
import com.andrbezr2016.library.catalog.service.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
}
