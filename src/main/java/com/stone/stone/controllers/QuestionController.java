package com.stone.stone.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.stone.models.Question;
import com.stone.stone.services.ImageService;
import com.stone.stone.services.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ImageService imageService;

    
    @PostMapping("/")
    public Question addQuestion(@RequestParam("question") String questionData,@RequestPart(value = "images",required = false)List<MultipartFile>images) throws Exception{

        ObjectMapper objectMapper=new ObjectMapper();
        Question question=objectMapper.readValue(questionData, Question.class);
        // System.out.println(images);

        // return question;

        question=this.questionService.addQuestion(question);

        String quesId=question.getQuesId().toString();

        if(images!=null){
            imageService.saveQuestionImages(quesId, images);
        }
        
        return question;
        
    }       

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question){
        Question ques=questionService.updateQuestion(question);
        return ques;
    }

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId) throws Exception{
        questionService.deleteQuestion(quesId);
    }

    @GetMapping("/")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }


    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId){
        return questionService.getQuestion(quesId);
    }
    
}
