package com.review.everything.web.dto;

import com.review.everything.domain.reviews.Reviews;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewsListResponseDto {
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    public ReviewsListResponseDto(Reviews entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}
