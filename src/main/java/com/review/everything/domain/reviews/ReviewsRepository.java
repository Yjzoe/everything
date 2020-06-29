package com.review.everything.domain.reviews;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    @Query("SELECT r FROM Reviews r ORDER BY r.id DESC")
    Page<Reviews> findAllDesc(Pageable pageable);

    Page<Reviews> findByWriterOrderByModifiedDateDesc(String writer, Pageable pageable);

    Page<Reviews> findByWriterAndCategoryOrderByModifiedDateDesc(String writer, String category, Pageable pageable);
}

