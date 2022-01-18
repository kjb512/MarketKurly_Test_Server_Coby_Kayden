package com.example.demo.src.review;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.review.model.ReviewDto;
import com.example.demo.src.review.model.ReviewReq;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void creatReview(ReviewReq reviewReq) throws BaseException {
        try {
            String url = s3Uploader.upload(reviewReq.getMultipart(), "static");
            reviewDao.createReview(reviewReq.getReviewDto(),url);
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
