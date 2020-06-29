package com.review.everything.service.reviews;

import com.review.everything.domain.reviews.Reviews;
import com.review.everything.domain.reviews.ReviewsRepository;
import com.review.everything.web.dto.ReviewsListResponseDto;
import com.review.everything.web.dto.ReviewsResponseDto;
import com.review.everything.web.dto.ReviewsSaveRequestDto;
import com.review.everything.web.dto.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    @Transactional
    public Long save(ReviewsSaveRequestDto requestDto) {
        return reviewsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewsUpdateRequestDto requestDto) {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. 글번호 =" + id));
        reviews.update(requestDto.getCategory(), requestDto.getTitle(), requestDto.getContent(), requestDto.getImg(), requestDto.getOneSentence(), requestDto.getGpa());
        return id;
    }

    public ReviewsResponseDto findById(Long id) {
        Reviews entity = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. 글번호 =" + id));

        return new ReviewsResponseDto(entity);
    }

    //    작성자별 출력
    @Transactional
    public Page<ReviewsListResponseDto> findByWriter(String writer,Pageable pageable) {
        return reviewsRepository.findByWriterOrderByModifiedDateDesc(writer, pageable).map(ReviewsListResponseDto::new);
    }

    @Transactional
    public Page<ReviewsListResponseDto> findByWriterAndCategory(String writer, String category, Pageable pageable) {
        return reviewsRepository.findByWriterAndCategoryOrderByModifiedDateDesc(writer, category, pageable).map(ReviewsListResponseDto::new);
    }

    @Transactional
    public void delete(Long id) {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. 글번호 =" + id));
        reviewsRepository.delete(reviews);
    }
}
