package com.stone.stone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stone.stone.models.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{
    
}
