package com.review.everything.web.dto;

import com.review.everything.domain.reviews.Reviews;
import lombok.Getter;

@Getter
public class ReviewsResponseDto {
    private Long id;
    private String category;
    private String title;
    private String writer;
    private String content;
    private int gpa;
    private String img;
    private String oneSentence;

    public ReviewsResponseDto(Reviews entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.writer = entity.getWriter();
        this.content = entity.getContent();
        this.gpa = entity.getGpa();
        this.img = entity.getImg();
        this.oneSentence = entity.getOneSentence();
    }
}
