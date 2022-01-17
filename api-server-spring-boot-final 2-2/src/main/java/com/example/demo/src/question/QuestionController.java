package com.example.demo.src.question;

import com.example.demo.src.review.ReviewProvider;
import com.example.demo.src.review.ReviewService;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class QuestionController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final QuestionProvider questionProvider;
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final JwtService jwtService;


    public QuestionController(QuestionProvider questionProvider, QuestionService questionService, JwtService jwtService){
        this.questionProvider = questionProvider;
        this.questionService = questionService;
        this.jwtService = jwtService;
    }
}
