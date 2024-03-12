package com.example.question.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
}
