package com.stone.stone.repository;

// import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stone.stone.models.Game;
import com.stone.stone.models.Review;
import java.util.List;



public interface ReviewRepository extends JpaRepository<Review,Long> {

    // public Set<Review>  findByGame(Game game);
    public List<Review> findByGame(Game game);

    
}
