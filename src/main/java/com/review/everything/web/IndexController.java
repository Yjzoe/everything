package com.review.everything.web;

import com.review.everything.config.auth.LoginUser;
import com.review.everything.config.auth.dto.SessionUser;
import com.review.everything.service.reviews.ReviewsService;
import com.review.everything.web.dto.ReviewsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ReviewsService reviewsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("reviews", reviewsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/reviews/save")
    public String reviewsSave() {
        return "reviews-save";
    }

    @GetMapping("/reviews/update/{id}")
    public String reviewsUpdate(@PathVariable Long id, Model model) {
        ReviewsResponseDto dto = reviewsService.findById(id);
        model.addAttribute("review", dto);
        return "reviews-update";
    }

}
