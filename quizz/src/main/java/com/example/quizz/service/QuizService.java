package com.example.quizz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizz.Feign.QuizFeign;
import com.example.quizz.model.QuestionWrapper;
import com.example.quizz.model.Quiz;
import com.example.quizz.model.Response;
import com.example.quizz.repo.QuizDao;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizFeign quizFeign;

    public ResponseEntity<String> createQuiz(String category, String title, int numQ) {
        List<Integer> questions = quizFeign.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz is success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questions = quiz.getQuestion();
        ResponseEntity<List<QuestionWrapper>> questionWrappers = quizFeign.getQuestionsFromId(questions);

        return questionWrappers;
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {

        ResponseEntity<Integer> mark = quizFeign.getScore(responses);

        return mark;

    }

}
