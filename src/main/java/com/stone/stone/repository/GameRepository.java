package com.stone.stone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stone.stone.models.Game;

import java.util.List;


public interface GameRepository extends JpaRepository<Game,Long>{
    public Game findByGamename(String gamename);

    @Query(value = "SELECT * FROM games WHERE discount >= 10", nativeQuery = true)
    public List<Game>findRandomGamesWithDiscount();

    
    @Query(value = "SELECT * FROM games WHERE price < :price", nativeQuery = true)
    public List<Game>findGamesLessThanPrice(Long price);

    public List<Game>findByCategoriesContains(String category);



}
