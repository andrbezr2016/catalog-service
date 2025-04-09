package com.andrbezr2016.library.catalog.repository;

import com.andrbezr2016.library.catalog.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
}
