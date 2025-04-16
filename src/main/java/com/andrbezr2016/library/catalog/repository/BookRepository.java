package com.andrbezr2016.library.catalog.repository;

import com.andrbezr2016.library.catalog.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    @Query("FROM Book WHERE id NOT IN :ids")
    List<Book> findAllExcludingIds(@Param("ids") Iterable<UUID> ids);
}
