package cn.letsky.movie.controller.api;

import cn.letsky.movie.entity.Review;
import cn.letsky.movie.form.ReviewForm;
import cn.letsky.movie.repository.ReviewRepository;
import cn.letsky.movie.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @PostMapping
    public ResponseEntity addReview(@RequestBody @Valid ReviewForm reviewForm, BindingResult bindingResult) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewForm, review);
        review.setCreateTime(new Date());
        int result = reviewRepository.insert(review);
        CommonUtils.checkInsert(result);
        return ResponseEntity.ok().build();
    }
}
