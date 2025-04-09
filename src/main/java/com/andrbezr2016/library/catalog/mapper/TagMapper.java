package com.andrbezr2016.library.catalog.mapper;

import com.andrbezr2016.library.catalog.dto.TagDto;
import com.andrbezr2016.library.catalog.dto.TagInput;
import com.andrbezr2016.library.catalog.dto.TagUpdate;
import com.andrbezr2016.library.catalog.entity.Tag;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto toDto(Tag tag);

    Collection<TagDto> toDtoCollection(Collection<Tag> tags);

    Tag toEntity(TagInput tagInput);

    Tag toEntity(TagUpdate tagUpdate);
}
