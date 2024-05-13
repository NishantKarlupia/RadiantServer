package com.stone.stone.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stone.stone.models.Experience;

@Service
public interface ExperienceService {
    public Experience addExperience(Experience experience);
    public Experience updateExperience(Experience experience);
    public void deleteExperience(Long eid) throws Exception;
    public List<Experience>getAllExperiences();
    public Experience getExperience(Long eid);
}
