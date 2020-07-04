package com.review.everything.domain.reviews;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    @Query("SELECT r FROM Reviews r ORDER BY r.id DESC")
    Page<Reviews> findAllDesc(Pageable pageable);

    Page<Reviews> findByWriter(String writer, Pageable pageable);
    Page<Reviews> findByWriterAndTitleContaining(String writer, String keyword, Pageable pageable);
    Page<Reviews> findByWriterAndContentContaining(String writer, String keyword, Pageable pageable);

    Page<Reviews> findByWriterAndCategory(String writer, String category, Pageable pageable);
    Page<Reviews> findByWriterAndCategoryAndTitleContaining(String writer, String category, String keyword, Pageable pageable);
    Page<Reviews> findByWriterAndCategoryAndContentContaining(String writer, String category, String keyword, Pageable pageable);
}

