package com.example.demo.src.question.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class QuestionReq {
    private int userIdx;
    private int productIdx;
    private String title;
    private String question;
    private String isLock;
}
