package com.stone.stone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stone.stone.models.Game;


public interface GameRepository extends JpaRepository<Game,Long>{
    public Game findByGamename(String gamename);
}
