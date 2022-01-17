package com.example.demo.src.question;


import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service Create, Update, Delete 의 로직 처리
@Service
public class QuestionService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final QuestionDao questionDao;
    private final QuestionProvider questionProvider;
    private final JwtService jwtService;


    @Autowired
    public QuestionService(QuestionDao questionDao, QuestionProvider questionProvider, JwtService jwtService) {
        this.questionDao = questionDao;
        this.questionProvider = questionProvider;
        this.jwtService = jwtService;

    }
}
