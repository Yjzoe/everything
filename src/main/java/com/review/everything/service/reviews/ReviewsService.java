package com.review.everything.service.reviews;

import com.review.everything.domain.reviews.Reviews;
import com.review.everything.domain.reviews.ReviewsRepository;
import com.review.everything.web.dto.ReviewsListResponseDto;
import com.review.everything.web.dto.ReviewsResponseDto;
import com.review.everything.web.dto.ReviewsSaveRequestDto;
import com.review.everything.web.dto.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<ReviewsListResponseDto> findAllDesc() {
        return reviewsRepository.findAllDesc().stream()
                .map(ReviewsListResponseDto::new)
                .collect(Collectors.toList());
    }

//    작성자별 출력
    @Transactional
    public List<ReviewsListResponseDto> findByWriter(String writer) {
        return reviewsRepository.findByWriterOrderByModifiedDateDesc(writer).stream()
                .map(ReviewsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. 글번호 =" + id));
        reviewsRepository.delete(reviews);
    }
}
