package com.andrbezr2016.library.catalog.mapper;

import com.andrbezr2016.library.catalog.dto.BookDto;
import com.andrbezr2016.library.catalog.dto.BookFilter;
import com.andrbezr2016.library.catalog.dto.BookInput;
import com.andrbezr2016.library.catalog.dto.BookUpdate;
import com.andrbezr2016.library.catalog.entity.Book;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Collection<BookDto> toDtoCollection(Collection<Book> books);

    Book toEntity(BookInput bookInput);

    Book toEntity(BookUpdate bookUpdate);

    Book toEntity(BookFilter bookFilter);
}
