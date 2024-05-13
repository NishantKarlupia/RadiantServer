package com.stone.stone.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stone.stone.models.Answer;

@Service
public interface AnswerService {

    public Answer addAnswer(Answer answer);
    public void deleteAnswer(Long ansId);
    // public Answer updateAnswer(Answer answer);
    public List<Answer>getAnswersOfQuestion(Long quesId);

}
