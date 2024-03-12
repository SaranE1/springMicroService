package com.example.quizz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizz.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
