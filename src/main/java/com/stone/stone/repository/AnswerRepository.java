package com.stone.stone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stone.stone.models.Answer;
import com.stone.stone.models.Question;

import java.util.List;


public interface AnswerRepository extends JpaRepository<Answer,Long>{
    
    public List<Answer> findByQuestion(Question question);
    
}
