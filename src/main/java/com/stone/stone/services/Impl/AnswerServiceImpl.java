package com.stone.stone.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.stone.models.Answer;
import com.stone.stone.models.Question;
import com.stone.stone.repository.AnswerRepository;
import com.stone.stone.services.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @SuppressWarnings("null")
    @Override
    public Answer addAnswer( Answer answer) {

        Answer ans=answerRepository.save(answer);
        return ans;
                
    }

    @SuppressWarnings("null")
    @Override
    public void deleteAnswer(Long ansId) {
        answerRepository.deleteById(ansId);
    }

    @Override
    public List<Answer> getAnswersOfQuestion(Long quesId) {
        Question question=new Question();
        question.setQuesId(quesId);

        List<Answer>answers=answerRepository.findByQuestion(question);

        return answers;
    }

    @Override
    public void likeAnswer(Long ansId) {
        Answer answer=this.answerRepository.findById(ansId).get();
        answer.setLikes(answer.getLikes()+1);
        this.answerRepository.save(answer);
    }

    @Override
    public void dislikeAnswer(Long ansId) {

        Answer answer=this.answerRepository.findById(ansId).get();
        answer.setDislikes(answer.getDislikes()+1);
        this.answerRepository.save(answer);
        
    }

   
    
}
