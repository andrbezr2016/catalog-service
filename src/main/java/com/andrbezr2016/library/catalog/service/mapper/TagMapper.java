package com.andrbezr2016.library.catalog.service.mapper;

import com.andrbezr2016.library.catalog.service.dto.TagDto;
import com.andrbezr2016.library.catalog.service.dto.TagInput;
import com.andrbezr2016.library.catalog.service.dto.TagUpdate;
import com.andrbezr2016.library.catalog.service.entity.Tag;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto toDto(Tag tag);

    Collection<TagDto> toDtoCollection(Collection<Tag> tags);

    Tag toEntity(TagInput tagInput);

    Tag toEntity(TagUpdate tagUpdate);
}
