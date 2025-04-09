package com.andrbezr2016.library.catalog.service.mapper;

import com.andrbezr2016.library.catalog.service.dto.BookDto;
import com.andrbezr2016.library.catalog.service.dto.BookInput;
import com.andrbezr2016.library.catalog.service.dto.BookUpdate;
import com.andrbezr2016.library.catalog.service.entity.Book;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Collection<BookDto> toDtoCollection(Collection<Book> books);

    Book toEntity(BookInput bookInput);

    Book toEntity(BookUpdate bookUpdate);
}
