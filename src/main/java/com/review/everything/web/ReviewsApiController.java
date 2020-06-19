package com.review.everything.web;

import com.review.everything.service.reviews.ReviewsService;
import com.review.everything.web.dto.ReviewsResponseDto;
import com.review.everything.web.dto.ReviewsSaveRequestDto;
import com.review.everything.web.dto.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewsApiController {

    private final ReviewsService service;

    @PostMapping("/api/v1/reviews")
    public Long save(@RequestBody ReviewsSaveRequestDto requestDto) {
        return service.save(requestDto);
    }

    @PutMapping("/api/v1/reviews/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewsUpdateRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @GetMapping("/api/v1/reviews/{id}")
    public ReviewsResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/api/v1/reviews/{id}")
    public Long delete(@PathVariable Long id) {
        service.delete(id);
        return id;
    }
//      삭제권한
//    @DeleteMapping("/api/v1/reviews/{id}")
//    public Long delete(@PathVariable Long id, @LoginUser SessionUser user) {
//        if (user.getName().equals(service.findById(id).getWriter())) {
//            service.delete(id);
//            return id;
//        }
//        return 0L;
//    }
}
