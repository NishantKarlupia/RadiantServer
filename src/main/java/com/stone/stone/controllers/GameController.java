package com.stone.stone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.stone.models.Game;
import com.stone.stone.services.GameService;
import com.stone.stone.services.ImageService;

@RestController
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ImageService imageService;

    // ,@RequestParam("coverImage") MultipartFile image
    @PostMapping(value = "/add")
    public Game addGame(@RequestParam("gameData") String gameDataString,@RequestPart("coverImage") MultipartFile coverImage,@RequestPart(value = "gamePlayImages",required = false) List<MultipartFile> gamePlayImages) throws Exception
    {

        ObjectMapper objectMapper = new ObjectMapper();
        Game game = objectMapper.readValue(gameDataString, Game.class);

        

        // return game;
        Game savedGame=this.gameService.addGame(game);

        String gid=savedGame.getGid().toString();
        imageService.saveCoverImage(gid, coverImage);

        imageService.saveGameplayImages(gid, gamePlayImages);

        return savedGame;
    }

    @PutMapping(value = "/update")
    public Game updateGame(@RequestParam("gameData") String gameDataString,@RequestPart(value = "coverImage",required = false) MultipartFile coverImage,@RequestPart(value = "gamePlayImages",required = false) List<MultipartFile> gamePlayImages) throws Exception
    {
        // game.setImage(image.getBytes());
        // System.out.println(game.getGamename());

        ObjectMapper objectMapper = new ObjectMapper();
        Game game = objectMapper.readValue(gameDataString, Game.class);

        // Game savedGame=this.gameService.getGameById(game.getGid());
        
        // return savedGame;
        return this.gameService.updateGame(game);
    }


    @GetMapping("/")
    public List<Game>getAllGames(){
        return this.gameService.getAllGames();
    }
    
    @GetMapping("/game/{gamename}")
    public Game getGame(@PathVariable("gamename") String gamename){
        return this.gameService.getGame(gamename);
    }
    
    @GetMapping("/{gameId}")
    public Game getGameById(@PathVariable("gameId") Long gameId){
        return this.gameService.getGameById(gameId);
    }

    @GetMapping("/random")
    public List<Game> getRandomGames(){
        return this.gameService.getRandomGames();
    }
    @GetMapping("/random/discount")
    public List<Game>getRandomGamesWithDiscout(){
        return this.gameService.getRandomGamesWithDiscount();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable("gameId") Long gameId){
        this.gameService.deleteGame(gameId);
    }
    
}




// if(coverImage!=null){
        //     game.setImage(coverImage.getBytes());
        // }


        // if(gamePlayImages!=null){
        //     List<byte[]> gamePlayImagesBytes = new ArrayList<>();
        //     for (MultipartFile gamePlayImage : gamePlayImages) {
        //         gamePlayImagesBytes.add(gamePlayImage.getBytes());
        //     }
        //     game.setImages(gamePlayImagesBytes);
        // }