package com.stone.stone.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.stone.stone.models.Answer;
import com.stone.stone.services.AnswerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/answer")
@CrossOrigin("*")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/")
    public Answer addAnswer(@RequestBody Answer answer){
        Answer ans=answerService.addAnswer(answer);
        return ans;
    }

    @DeleteMapping("/{ansId}")
    public void deleteAnswer(@PathVariable("ansId") Long ansId){
        answerService.deleteAnswer(ansId);
    }

    @GetMapping("/question/{quesId}")
    public List<Answer>getAnswersOfQuestion(@PathVariable("quesId")Long quesId){
        return answerService.getAnswersOfQuestion(quesId);
    }

    @PutMapping("/like/{ansId}")
    public void likeAnswer(@PathVariable("ansId")Long ansId){
        answerService.likeAnswer(ansId);
    }

    @PutMapping("/dislike/{ansId}")
    public void dislikeAnswer(@PathVariable("ansId")Long ansId){
        answerService.dislikeAnswer(ansId);
    }

    
}
