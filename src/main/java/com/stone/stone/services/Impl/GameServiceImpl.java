package com.stone.stone.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stone.stone.models.Game;
import com.stone.stone.repository.GameRepository;
import com.stone.stone.services.GameService;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game addGame(Game game) throws Exception {
        Game newGame=this.gameRepository.findByGamename(game.getGamename());
        if(newGame!=null)
        {
            throw new Exception("Game Already Exists");
        }

        newGame=this.gameRepository.save(game);
        return newGame;
    }

    @SuppressWarnings("null")
    @Override
    public void deleteGame(Long id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public Game getGame(String gamename) {
        return this.gameRepository.findByGamename(gamename);
    }

    @SuppressWarnings("null")
    @Override
    public Game getGameById(Long id) {

        Game game=this.gameRepository.findById(id).get();
        String base64Image;
        try {
            base64Image = getImageBase64(id,"coverImage.jpg");
        } catch (IOException e) {
            base64Image="";
        }
        game.setCoverImage(base64Image);



        List<String> gamePlayImagesBase64 = new ArrayList<>();
        try {
            Path gamePlayImagesDir = Paths.get("uploads", id.toString(), "gameplayImages");
            Files.walk(gamePlayImagesDir)
                 .filter(Files::isRegularFile)
                 .forEach(imagePath -> {
                     try {
                         String imageBase64 = getImageBase64(id, "gamePlayImages/"+imagePath.getFileName().toString());
                         gamePlayImagesBase64.add(imageBase64);
                     } catch (IOException e) {
                         // Handle error or continue without the game play image
                         gamePlayImagesBase64.add(null);
                     }
                 });
        } catch (IOException e) {
            
        }


        game.setGamePlayImages(gamePlayImagesBase64);

        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game>games=this.gameRepository.findAll();
        for (Game game : games) {
            try {
                String coverImageBase64 = getImageBase64(game.getGid(), "coverImage.jpg");
                game.setCoverImage(coverImageBase64);

                List<String> gamePlayImagesBase64 = new ArrayList<>();
                Path gamePlayImagesDir = Paths.get("uploads", game.getGid().toString(), "gameplayImages");
                Files.walk(gamePlayImagesDir)
                     .filter(Files::isRegularFile)
                     .forEach(imagePath -> {
                         try {
                             String imageBase64 = getImageBase64(game.getGid(), "gameplayImages/" + imagePath.getFileName());
                             gamePlayImagesBase64.add(imageBase64);
                         } catch (IOException e) {
                             // Handle error or continue without the game play image
                         }
                     });
                game.setGamePlayImages(gamePlayImagesBase64);
            } catch (IOException e) {
                // Handle error
            }
        }

        return games;
    }

    @Override
    public Game updateGame(Game game) {
        return this.gameRepository.save(game);
    }

    // public String getImageBase64(Long gameId) throws IOException {
    //     Path imagePath = Paths.get("uploads", gameId.toString(), "coverImage.jpg");
    //     byte[] imageData = Files.readAllBytes(imagePath);
    //     String base64Image = Base64.getEncoder().encodeToString(imageData);
    //     return base64Image;
    // }
    
    private String getImageBase64(Long gameId, String imageFileName) throws IOException {
        Path imagePath = Paths.get("uploads", gameId.toString(), imageFileName);
        byte[] imageData = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(imageData);
    }
    
}
