package com.stone.stone.services;

import java.util.List;
// import java.util.Set;

import org.springframework.stereotype.Service;

// import com.stone.stone.models.Game;
import com.stone.stone.models.Review;

@Service
public interface ReviewService {
    public Review addReview(Review review);
    public void deleteReview(Long id);
    public List<Review> getReviewOfGame(Long gameId);
    public List<Review>getAllReviews();
}
