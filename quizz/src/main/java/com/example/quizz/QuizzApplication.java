package com.example.quizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzApplication.class, args);
		System.out.println("Quiz started");
	}

}
