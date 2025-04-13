package com.andrbezr2016.library.catalog.repository;

import com.andrbezr2016.library.catalog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID>, JpaSpecificationExecutor<Tag> {

    List<Tag> findAllByNameIn(Iterable<String> names);

    default Map<String, Tag> findAllByNameInUsingMap(Iterable<String> names) {
        return findAllByNameIn(names).stream().collect(Collectors.toMap(Tag::getName, Function.identity()));
    }
}
