package com.stone.stone.services;

import java.util.List;

import org.springframework.stereotype.Service;

// import com.stone.stone.models.Answer;
import com.stone.stone.models.Question;

@Service
public interface QuestionService {
    
    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public void deleteQuestion(Long quesId) throws Exception;
    public List<Question>getAllQuestions();
    public Question getQuestion(Long quesId);
    // public List<Answer>getAnswersOfQuestion(Long quesId);
    
}