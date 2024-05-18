package com.stone.stone.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stone.stone.models.Game;

@Service
public interface GameService {
    public Game addGame(Game game) throws Exception;
    public Game getGame(String gamename);
    public Game getGameById(Long id);
    public Game updateGame(Game game);
    public void deleteGame(Long id);
    public List<Game> getAllGames();
    public List<Game>getRandomGames();
    public List<Game>getRandomGamesWithDiscount();
}
