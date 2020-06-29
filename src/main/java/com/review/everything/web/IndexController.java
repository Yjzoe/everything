package com.review.everything.web;

import com.review.everything.config.auth.LoginUser;
import com.review.everything.config.auth.dto.SessionUser;
import com.review.everything.service.reviews.ReviewsService;
import com.review.everything.web.dto.ReviewsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ReviewsService reviewsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "all") String category, Pageable pageable) {
        if (user != null) {
            if (category.equals("all")) {
                model.addAttribute("reviews", reviewsService.findByWriter(user.getName(), pageable));
            } else {
                model.addAttribute("reviews", reviewsService.findByWriterAndCategory(user.getName(), category, pageable));
            }
            model.addAttribute("userName", user.getName());
            model.addAttribute("category", category);
        }
        return "index";
    }

    @GetMapping("/reviews/save")
    public String reviewsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "reviews-save";
    }

    @GetMapping("/reviews/update/{id}")
    public String reviewsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        ReviewsResponseDto dto = reviewsService.findById(id);
        if (user.getName().equals(dto.getWriter())) {
            model.addAttribute("review", dto);
            return "reviews-update";
        }
        model.addAttribute("msg","접근 권한이 없습니다.");
        return "index";
    }

}
