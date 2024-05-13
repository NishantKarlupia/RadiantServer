package com.stone.stone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stone.stone.models.Question;
import com.stone.stone.services.QuestionService;
import com.stone.stone.services.UserService;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;


    // user-controller
    @PutMapping("/addtocart/{userId}/{gameId}")
    public void addToCart(@PathVariable("userId") Long userId,@PathVariable("gameId") Long gameId) throws Exception{
        this.userService.addToCart(userId, gameId);
    }

    @PutMapping("/purchasegame/{userId}/{gameId}")
    public void purchaseGame(@PathVariable("userId") Long userId,@PathVariable("gameId") Long gameId) throws Exception{
        this.userService.purchaseGame(userId, gameId);
    }

    

    // question-controller
    @PostMapping("")
    public Question addQuestion(@RequestBody Question question){

        Question ques=questionService.addQuestion(question);
        return ques;
    }       

    @PutMapping("")
    public Question updateQuestion(@RequestBody Question question){
        Question ques=questionService.updateQuestion(question);
        return ques;
    }

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId) throws Exception{
        questionService.deleteQuestion(quesId);
    }
    
    
}
