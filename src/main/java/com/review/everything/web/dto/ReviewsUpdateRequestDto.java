package com.review.everything.web.dto;

import com.review.everything.domain.reviews.Reviews;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewsUpdateRequestDto {
    private String category;
    private String title;
    private String content;
    private int rate;
    private String img;
    private String oneSentence;

    @Builder
    public ReviewsUpdateRequestDto(String category, String title, String content, String img, String oneSentence, int rate) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.img = img;
        this.oneSentence = oneSentence;
    }

    public Reviews toEntity() {
        return Reviews.builder()
                .category(category)
                .title(title)
                .content(content)
                .rate(rate)
                .img(img)
                .oneSentence(oneSentence)
                .build();
    }
}
