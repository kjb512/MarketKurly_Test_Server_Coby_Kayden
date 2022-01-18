package com.example.demo.src.question;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.question.model.AnswerReq;
import com.example.demo.src.question.model.QuestionReq;
import com.example.demo.src.question.model.QuestionRes;
import com.example.demo.src.review.ReviewProvider;
import com.example.demo.src.review.ReviewService;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
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

    @ResponseBody
    @GetMapping("/{productIdx}")
    public BaseResponse<List<QuestionRes>> getQuestionsByProductIdx(@PathVariable("productIdx") int productIdx) {
        // Get Users
        try{
            List<QuestionRes> getQuestionRes = questionProvider.getQuestionsWithAnswer(productIdx);
            return new BaseResponse<>(getQuestionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<List<QuestionRes>> createQuestion(@RequestBody QuestionReq questionReq) {
        // Get Users
        try{
            List<QuestionRes> getQuestionRes = questionService.createQuestion(questionReq.getProductIdx(),questionReq.getUserIdx(),
                    questionReq.getTitle(),questionReq.getQuestion(),questionReq.getIsLock());
            return new BaseResponse<>(getQuestionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("")
    public BaseResponse<List<QuestionRes>> updateQuestion(@RequestParam(required = false) int productIdx,
                                                          @RequestParam(required = false) int questionIdx,
                                                          @RequestParam(required = false) String title ,
                                                          @RequestParam(required = false) String question,
                                                          @RequestParam(required = false) String isLock) {

        try{
            List<QuestionRes> getQuestionRes = questionService.updateQuestion(productIdx, questionIdx, title, question, isLock);
            return new BaseResponse<>(getQuestionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/{productIdx}")
    public BaseResponse<List<QuestionRes>> createAnswer(@RequestBody AnswerReq answerReq) {
        // Get Users
        try{
            List<QuestionRes> getQuestionRes = questionService.createAnswer(answerReq.getProductIdx(), answerReq.getQuestionIdx(), answerReq.getAnswer());
            return new BaseResponse<>(getQuestionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/deletion")
    public BaseResponse<List<QuestionRes>> deleteQuestion(@RequestParam(required = false) int productIdx,
                                                          @RequestParam(required = false) int questionIdx) {
        // Get Users
        try{
            List<QuestionRes> getQuestionRes = questionService.deleteQuestion(productIdx, questionIdx);
            return new BaseResponse<>(getQuestionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }






}
