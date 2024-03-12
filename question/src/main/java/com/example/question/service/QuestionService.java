package com.example.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.question.model.QuestionModel;
import com.example.question.model.QuestionWrapper;
import com.example.question.model.Response;
import com.example.question.repo.QuestionDao;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionModel>> getByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("null")
    public ResponseEntity<String> addQuestion(QuestionModel questionModel) {

        questionDao.save(questionModel);
        return new ResponseEntity<>("success 😉", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestion);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsId) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for (Integer id : questionsId) {
            QuestionModel questionModel = questionDao.findById(id).get();
            QuestionWrapper questionWrapper = new QuestionWrapper(questionModel.getId(),
                    questionModel.getQuestionTitle(), questionModel.getOpt1(), questionModel.getOpt2(),
                    questionModel.getOpt3(), questionModel.getOpt4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {

        int mark = 0;
        for (Response response : responses) {
            QuestionModel questionModel = questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(questionModel.getCorrectAnswer()))
                mark++;
        }

        return new ResponseEntity<>(mark, HttpStatus.OK);
    }
}
