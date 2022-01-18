package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.product.ProductService;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.src.review.model.ReviewDto;
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
    public BaseResponse<List<ReviewRes>> creatReview(@ModelAttribute ReviewReq reviewReq) throws IOException {
        try {
            reviewService.creatReview(reviewReq);
            return null;
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
