package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.product.ProductService;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.src.question.model.QuestionRes;
import com.example.demo.src.review.model.ReviewDto;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewReq;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final S3Uploader s3Uploader;

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;


    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService, S3Uploader s3Uploader) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
        this.s3Uploader = s3Uploader;
    }

    // return 값
    @PostMapping("")
    public BaseResponse<ReviewInfoRes> creatReview(@ModelAttribute ReviewReq reviewReq) throws IOException {
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(reviewReq.getReviewDto().getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            ReviewInfoRes reviewInfoRes = reviewService.creatReview(reviewReq);
            return new BaseResponse<>(reviewInfoRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{reviewIdx}")
    public BaseResponse<ReviewInfoRes> getReviewInfo(@PathVariable("reviewIdx") int reviewIdx) {
        // Get Users
        try{
            ReviewInfoRes getReviewInfoRes = reviewProvider.getReview(reviewIdx);
            return new BaseResponse<>(getReviewInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<ReviewRes>> getReviewList(@RequestParam(required = false) int productIdx) {
        // Get Users
        try{
            List<ReviewRes> getReviewRes = reviewProvider.getReviewList(productIdx);
            return new BaseResponse<>(getReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("")
    public BaseResponse<ReviewInfoRes> updateReview(@RequestParam(required = false) int reviewIdx,
                                                    @RequestParam(required = false) String title,
                                                    @RequestParam(required = false) String content) {
        // Get Users
        try{
            ReviewInfoRes getReviewInfoRes = reviewService.updateReview(title,content,reviewIdx);
            return new BaseResponse<>(getReviewInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @PatchMapping("/deletion")
    public BaseResponse<List<ReviewRes>> deleteReview(@RequestParam(required = false) int reviewIdx,
                                                    @RequestParam(required = false) int productIdx) {
        // Get Users
        try{
            List<ReviewRes> getReviewInfoRes = reviewService.deleteReview(reviewIdx, productIdx);
            return new BaseResponse<>(getReviewInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
