package com.stone.stone.services.Impl;

import java.util.List;
// import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.stone.models.Game;
import com.stone.stone.models.Review;
import com.stone.stone.repository.GameRepository;
import com.stone.stone.repository.ReviewRepository;
import com.stone.stone.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @SuppressWarnings("null")
    @Override
    public Review addReview(Review review) {
        return this.reviewRepository.save(review);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteReview(Long id) {
        this.reviewRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    @Override
    public List<Review> getReviewOfGame(Long gameId) {
        Game game=this.gameRepository.findById(gameId).get();
        return this.reviewRepository.findByGame(game);
    }

    @Override
    public List<Review> getAllReviews() {
        return this.reviewRepository.findAll();
    }
    
}
