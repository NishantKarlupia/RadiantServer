package com.stone.stone.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.stone.stone.models.Answer;
import com.stone.stone.models.Question;
// import com.stone.stone.repository.AnswerRepository;
import com.stone.stone.repository.QuestionRepository;
import com.stone.stone.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // @Autowired
    // private AnswerRepository answerRepository;

    @SuppressWarnings("null")
    @Override
    public Question addQuestion(Question question) {
        Question ques=questionRepository.save(question);
        return ques;
    }
    
    @SuppressWarnings("null")
    @Override
    public Question updateQuestion(Question question) {
        Question ques=questionRepository.save(question);
        return ques;
    }
    
    @SuppressWarnings("null")
    @Override
    public void deleteQuestion(Long quesId) throws Exception {
        Question ques=questionRepository.findById(quesId).get();
        if(ques==null){
            throw new Exception("no such question exists");
        }
        questionRepository.deleteById(quesId);
    }

    @Override
    public List<Question> getAllQuestions() {
       List<Question>questions=questionRepository.findAll();
       return questions;
    }

    @SuppressWarnings("null")
    @Override
    public Question getQuestion(Long quesId) {
        Question question=questionRepository.findById(quesId).get();

        List<String>questionImages=new ArrayList<>();

        try {

        Path imagesDir = Paths.get("uploads/questions", quesId.toString());

        Files.walk(imagesDir)
        .filter(Files::isRegularFile)
        .forEach(imagePath->{
            try {
                // System.out.println(imagePath);
                String imageBase64 = getImageBase64(quesId,imagePath.getFileName().toString());
                questionImages.add(imageBase64);
            } catch (Exception e) {
                // TODO: handle exception
            }
        });           
        } catch (Exception e) {
            // TODO: handle exception
        }

        // System.out.println(questionImages);
        // System.out.println("hello world inside question service");

        question.setImages(questionImages);


        return question;
    }

    // @Override
    // public List<Answer> getAnswersOfQuestion(Long quesId) {
    //     Question question=new Question();
    //     question.setQuesId(quesId);

    //     List<Answer>answers=answerRepository.findByQuestion(question);

    //     return answers;
    // }

    private String getImageBase64(Long quesId, String imageFileName) throws IOException {
        Path imagePath = Paths.get("uploads/questions", quesId.toString(), imageFileName);
        byte[] imageData = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(imageData);
    }
    
}
