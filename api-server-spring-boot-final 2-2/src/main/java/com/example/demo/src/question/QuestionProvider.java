package com.example.demo.src.question;


import com.example.demo.src.review.ReviewDao;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Provider : Read의 비즈니스 로직 처리
@Service
public class QuestionProvider {

    private final QuestionDao questionDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public QuestionProvider(QuestionDao questionDao, JwtService jwtService) {
        this.questionDao = questionDao;
        this.jwtService = jwtService;
    }
}
