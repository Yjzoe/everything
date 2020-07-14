package com.review.everything.web;

import com.review.everything.service.reviews.ReviewsService;
import com.review.everything.web.dto.ReviewsResponseDto;
import com.review.everything.web.dto.ReviewsSaveRequestDto;
import com.review.everything.web.dto.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RequiredArgsConstructor
@RestController
public class ReviewsApiController {


    @Value("${storage.location}")
    private String fileRealPath;

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
        String fileName = findById(id).getImg();
        File file = new File(fileRealPath + fileName);
        if(file.exists() == true){
            file.delete();
        }
        service.delete(id);
        return id;
    }
}
