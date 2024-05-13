package com.stone.stone.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Value("${images.upload.directory}")
    private String uploadDirectory;

    public void saveCoverImage(String gid, MultipartFile coverImage) throws IOException {
        String gameDirectory = Paths.get(uploadDirectory, gid).toString();
        File directory = new File(gameDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Path coverImagePath = Paths.get(gameDirectory, "coverImage.jpg");
        coverImage.transferTo(coverImagePath);
    }

    public void saveGameplayImages(String gid, List<MultipartFile> gameplayImages) throws IOException {
        String gameDirectory = Paths.get(uploadDirectory, gid, "gameplayImages").toString();
        File directory = new File(gameDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        for (MultipartFile gameplayImage : gameplayImages) {
            Path gameplayImagePath = Paths.get(gameDirectory, gameplayImage.getOriginalFilename());
            gameplayImage.transferTo(gameplayImagePath);
        }
    }


    public void saveQuestionImages(String quesId,List<MultipartFile> questionImages) throws Exception{
        String questionDirectory=Paths.get(uploadDirectory+"/questions", quesId).toString();

        File directory=new File(questionDirectory);
        if(!directory.exists()){
            directory.mkdirs();
        }

        for (MultipartFile image:questionImages) {
            Path imagePath = Paths.get(questionDirectory, image.getOriginalFilename());
            image.transferTo(imagePath);
        }
    }

    public void saveExperiencesImages(String expId,List<MultipartFile> questionImages) throws Exception{
        String questionDirectory=Paths.get(uploadDirectory+"/experiences", expId).toString();

        File directory=new File(questionDirectory);
        if(!directory.exists()){
            directory.mkdirs();
        }

        for (MultipartFile image:questionImages) {
            Path imagePath = Paths.get(questionDirectory, image.getOriginalFilename());
            image.transferTo(imagePath);
        }
    }


}
