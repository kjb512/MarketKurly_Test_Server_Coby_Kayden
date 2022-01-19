package com.example.demo.src.review;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.review.model.ReviewDto;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewReq;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.prefs.BackingStoreException;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

// Service Create, Update, Delete 의 로직 처리
@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final S3Uploader s3Uploader;

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;


    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService,S3Uploader s3Uploader) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;
        this.s3Uploader = s3Uploader;
    }

    public ReviewInfoRes creatReview(ReviewReq reviewReq) throws BaseException {
        try {
            String url = s3Uploader.upload(reviewReq.getMultipart(), "static");

            return reviewProvider.getReview(reviewDao.createReview(reviewReq.getReviewDto(),url));
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public ReviewInfoRes updateReview(String title, String content, int reviewIdx) throws BaseException{
        try {
            reviewDao.updateReview(title, content, reviewIdx);
            return reviewProvider.getReview(reviewIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<ReviewRes> deleteReview(int reviewIdx,int productIdx) throws BaseException{
        try {
            reviewDao.deleteReviewList(reviewIdx);
            return reviewProvider.getReviewList(productIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
