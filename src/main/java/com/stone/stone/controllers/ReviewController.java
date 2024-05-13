package com.stone.stone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stone.stone.models.Review;
import com.stone.stone.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/")
    public Review addReview(@RequestBody Review review){
        return this.reviewService.addReview(review);
    }

    @GetMapping("/")
    public List<Review> getAllReviews(){
        return this.reviewService.getAllReviews();
    }

    @GetMapping("/{gameId}")
    public List<Review>getReviewsOfGame(@PathVariable("gameId")Long gameId){
        return this.reviewService.getReviewOfGame(gameId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId){
        this.reviewService.deleteReview(reviewId);
    }
    
}
