package com.example.demo.src.question;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.question.model.QuestionRes;
import com.example.demo.src.review.ReviewDao;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class QuestionProvider {

    private final QuestionDao questionDao;
    private final JwtService jwtService;

    Calendar cal = Calendar.getInstance();

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public QuestionProvider(QuestionDao questionDao, JwtService jwtService) {
        this.questionDao = questionDao;
        this.jwtService = jwtService;
    }

    public List<QuestionRes> getQuestionsWithAnswer(int productidx) throws BaseException {
        try{
            List<QuestionRes> questionResList = questionDao.getQuestionsWithAnswer(productidx);
            for(int i=0;i<questionResList.size();i++){;
                cal.setTime(questionResList.get(i).getCreateAt());
                questionResList.get(i).setCreatDate(String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));
            }

            return questionResList;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
