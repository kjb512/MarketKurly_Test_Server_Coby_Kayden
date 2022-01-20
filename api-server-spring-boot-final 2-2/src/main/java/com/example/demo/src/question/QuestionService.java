package com.example.demo.src.question;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.question.model.QuestionRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.CHECK_INVALID_QUESTION_OR_PRODUCT_ID;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

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

    public List<QuestionRes> createQuestion(int productIdx, int userIdx, String title, String question, String isLock) throws BaseException {
        try{
            questionDao.createQuestion(productIdx,userIdx,title,question,isLock);
            return questionProvider.getQuestionsWithAnswer(productIdx);
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<QuestionRes> updateQuestion(int productIdx,int questionIdx, String title, String question, String isLock) throws BaseException{
        try{
            questionDao.updateQuestion(questionIdx,title,question,isLock);
            return questionProvider.getQuestionsWithAnswer(productIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<QuestionRes> deleteQuestion(int productIdx, int questionIdx) throws BaseException {
        try {
            questionDao.deleteQuestion(questionIdx);
            return questionProvider.getQuestionsWithAnswer(productIdx);
        } catch (Exception exception) {
            throw new BaseException(CHECK_INVALID_QUESTION_OR_PRODUCT_ID);
        }
    }

    public List<QuestionRes> createAnswer(int productidx, int questionIdx, String answer) throws BaseException{
        try{
            questionDao.creatAnswer(questionIdx,answer);
            return questionProvider.getQuestionsWithAnswer(productidx);

        }catch (Exception exception){
            throw new BaseException(CHECK_INVALID_QUESTION_OR_PRODUCT_ID);
        }
    }

}
