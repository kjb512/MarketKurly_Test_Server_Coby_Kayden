package com.example.demo.src.question.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class QuestionRes {
    private int questionIdx;
    private int userIdx;
    private String title;
    private String question;
    private String isAnswer;
    private String isLock;
    private Timestamp createAt;
    private String creatDate;
    private AnswerRes answerRes;
}
