package com.review.everything.domain.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT r FROM Reviews r ORDER BY r.id DESC")
    List<Reviews> findAllDesc();
}
