package com.example.quizz.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizz.model.QuestionWrapper;
import com.example.quizz.model.Response;

@FeignClient("QUESTION")
public interface QuizFeign {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName,
            @RequestParam Integer numQuestion);

    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId);

    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}