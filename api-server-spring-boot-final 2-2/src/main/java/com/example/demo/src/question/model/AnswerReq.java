package com.example.demo.src.question.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerReq {
    private int questionIdx;
    private int productIdx;
    private String answer;
}
