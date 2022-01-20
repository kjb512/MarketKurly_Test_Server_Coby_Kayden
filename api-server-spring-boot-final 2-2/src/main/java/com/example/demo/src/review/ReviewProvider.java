package com.example.demo.src.review;


import com.example.demo.config.BaseException;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ReviewProvider {

    private final ReviewDao reviewDao;
    private final JwtService jwtService;

    Calendar cal = Calendar.getInstance();

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.jwtService = jwtService;
    }

    public List<ReviewRes> getReviewList(int productIdx) throws BaseException{
        try{
            List<ReviewRes> getReviewlist = reviewDao.getReviewList(productIdx);
            for(int i=0; i<getReviewlist.size();i++){
                cal.setTime(getReviewlist.get(i).getCreateAt());
                getReviewlist.get(i).setCreateDate(String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));
            }
            return getReviewlist;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public ReviewInfoRes getReview(int reviewIdx) throws BaseException{
        try{
            ReviewInfoRes reviewInfoRes = reviewDao.getReviewInfo(reviewIdx);
            cal.setTime(reviewInfoRes.getCreateAt());
            reviewInfoRes.setCreateDate(String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));
            return reviewInfoRes;

        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
