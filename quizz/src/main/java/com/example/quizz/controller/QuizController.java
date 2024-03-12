package com.example.quizz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizz.model.QuestionWrapper;
import com.example.quizz.model.QuizzDto;
import com.example.quizz.model.Response;
import com.example.quizz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizzDto quizzDto) {
        // return new ResponseEntity<String>("I'm here", HttpStatus.OK);
        return quizService.createQuiz(quizzDto.getCategory(), quizzDto.getTitle(), quizzDto.getNumQ());
    }

    @PostMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id) {

        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitResponse(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculate(id, responses);
    }
}
