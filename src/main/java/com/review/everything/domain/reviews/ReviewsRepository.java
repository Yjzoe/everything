package com.review.everything.domain.reviews;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT r FROM Reviews r ORDER BY r.id DESC")
    List<Reviews> findAllDesc();

    List<Reviews> findByWriterOrderByModifiedDateDesc(String writer);

    List<Reviews> findByWriterAndCategoryOrderByModifiedDateDesc(String writer, String category);
//    Page<Reviews> findByWriterAndCategoryOrderByModifiedDateDesc(String writer, String category);

}

