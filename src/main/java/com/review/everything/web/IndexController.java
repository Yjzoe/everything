package com.review.everything.web;

import com.review.everything.config.auth.LoginUser;
import com.review.everything.config.auth.dto.SessionUser;
import com.review.everything.service.reviews.ReviewsService;
import com.review.everything.web.dto.ReviewsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String index(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "all") String category,
                        @RequestParam(defaultValue = "") String searchCategory, @RequestParam(defaultValue = "") String keyword,
                        @PageableDefault(size = 3, page = 0 , sort = {"modifiedDate"}, direction = Sort.Direction.DESC ) Pageable pageable) {
        if (user != null) {
            if (!category.equals("all")) {
                if (!searchCategory.equals("") && !keyword.equals("")) {
                    if (searchCategory.equals("title")) {
                        model.addAttribute("reviews", reviewsService.findByWriterAndCategoryAndTitleContaining(user.getName(), category, keyword, pageable));
                    } else {
                        model.addAttribute("reviews", reviewsService.findByWriterAndCategoryAndContentContaining(user.getName(), category, keyword, pageable));
                    }
                } else {
                    model.addAttribute("reviews", reviewsService.findByWriterAndCategory(user.getName(), category, pageable));
                }
            } else {
                if (!searchCategory.equals("") && !keyword.equals("")) {
                    if (searchCategory.equals("title")) {
                        model.addAttribute("reviews", reviewsService.findByWriterAndTitleContaining(user.getName(), keyword, pageable));
                    } else {
                        model.addAttribute("reviews", reviewsService.findByWriterAndContentContaining(user.getName(), keyword, pageable));
                    }
                } else {
                    model.addAttribute("reviews", reviewsService.findByWriter(user.getName(), pageable));
                }
            }

            model.addAttribute("searchCategory", searchCategory);
            model.addAttribute("keyword", keyword);
            model.addAttribute("userName", user.getName());
            model.addAttribute("category", category);
            switch (category) {
                case "all": {
                    model.addAttribute("categoryKor", "all");
                    break;
                }
                case "movie": {
                    model.addAttribute("categoryKor", "영화");
                    break;
                }
                case "tvshow": {
                    model.addAttribute("categoryKor", "tv프로그램");
                    break;
                }
                case "book": {
                    model.addAttribute("categoryKor", "책");
                    break;
                }
            }
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

    @Value("${storage.location}")
    private String fileRealPath;

    @GetMapping("/reviews/update/{id}")
    public String reviewsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        ReviewsResponseDto dto = reviewsService.findById(id);
        if (user.getName().equals(dto.getWriter())) {
            if (dto.getImg().length()!=0) {
                model.addAttribute("img", fileRealPath + dto.getImg());
            } else {
                model.addAttribute("img", null);
            }
            model.addAttribute("review", dto);
            return "reviews-update";
        }
        model.addAttribute("msg","접근 권한이 없습니다.");
        return "index";
    }

}
