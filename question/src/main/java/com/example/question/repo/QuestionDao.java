package com.example.question.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.question.model.QuestionModel;

@Repository
public interface QuestionDao extends JpaRepository<QuestionModel, Integer> {

    List<QuestionModel> findByCategory(String category);

    @Query(value = "Select q.id from question_model q where q.category = :category Order by rand() limit :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

}