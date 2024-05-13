package com.stone.stone.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.stone.models.Experience;
import com.stone.stone.repository.ExperienceRepository;
import com.stone.stone.services.ExperienceService;

@Service
public class ExperienceServiceImpl implements ExperienceService{

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public Experience addExperience(Experience experience) {

        Experience createdExperience=experienceRepository.save(experience);
        return createdExperience;

    }

    @Override
    public Experience updateExperience(Experience experience) {
        Experience updatedExperience=experienceRepository.save(experience);
        return updatedExperience;
    }

    @Override
    public void deleteExperience(Long eid) throws Exception {
        Experience exp=experienceRepository.findById(eid).get();
        if(exp==null){
            throw new Exception("no experience found!!!");
        }
        experienceRepository.deleteById(eid);
        
    }

    @Override
    public List<Experience> getAllExperiences() {
        List<Experience>experiences=experienceRepository.findAll();
        return experiences;
    }

    @Override
    public Experience getExperience(Long eid) {
        Experience experience=experienceRepository.findById(eid).get();


        List<String>images=new ArrayList<>();

        try {
            Path imagesDir=Paths.get("uploads/experiences", eid.toString());

            Files.walk(imagesDir)
            .filter(Files::isRegularFile)
            .forEach(imagePath->{
                try {
                    String imagesBase64=getImageBase64(eid, imagePath.getFileName().toString());
                    images.add(imagesBase64);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            });


        } catch (Exception e) {
            // TODO: handle exception
        }

        experience.setImages(images);

        return experience;

    }

    private String getImageBase64(Long eid, String imageFileName) throws IOException {
        Path imagePath = Paths.get("uploads/experiences", eid.toString(), imageFileName);
        byte[] imageData = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(imageData);
    }
    
}
