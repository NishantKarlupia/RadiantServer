package com.stone.stone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.stone.models.Experience;
import com.stone.stone.services.ExperienceService;
import com.stone.stone.services.ImageService;

@RestController
@RequestMapping("/experience")
@CrossOrigin("*")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private ImageService imageService;


    @PostMapping("/")
    public Experience addExperience(@RequestParam("experience") String experienceData,@RequestPart(value = "images",required = false)List<MultipartFile>images) throws Exception{

        ObjectMapper objectMapper=new ObjectMapper();
        Experience experience=objectMapper.readValue(experienceData, Experience.class);
        
        experience=this.experienceService.addExperience(experience);

        String expId=experience.getEid().toString();

        // if(images!=null){
        //     imageService.saveExperiencesImages(expId, images);
        // }
        // System.out.println(images);

        return experience;

    }

    @DeleteMapping("/{eId}")
    public void deleteExperience(@PathVariable("eId")Long eId) throws Exception{
        experienceService.deleteExperience(eId);
    }

    @GetMapping("/")
    public List<Experience>getAllExperiences(){
        return experienceService.getAllExperiences();
    }

    @GetMapping("/{eId}")
    public Experience getExperience(@PathVariable("eId") Long eId){
        return experienceService.getExperience(eId);
    }

    
}
