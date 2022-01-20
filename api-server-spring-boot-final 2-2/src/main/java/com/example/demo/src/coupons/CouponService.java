package com.example.demo.src.coupons;

import com.example.demo.config.BaseException;
import com.example.demo.src.coupons.model.PostCouponReq;
import com.example.demo.src.coupons.model.PostCouponRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponDao couponDao;
    private final CouponProvider couponProvider;
    private final JwtService jwtService;

    public PostCouponRes createCouponUser(PostCouponReq postCouponReq) throws BaseException {
        int result = couponProvider.doubleCheckCoupon(postCouponReq.getUserIdx(), postCouponReq.getCouponIdx());
        if(result != 0){
            throw new BaseException(DUPLICATED_COUPON);
        }
        try{
            int couponUserIdx = couponDao.createCouponUser(postCouponReq);
            return new PostCouponRes(couponUserIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void cancelCouponUser(int userIdx, int couponUserIdx) throws  BaseException{

        try{
            int result = couponDao.cancelCouponUser(userIdx, couponUserIdx);
            if(result == 0){
                throw new BaseException(DELETION_FAIL_Like);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Scheduled(cron = "0 0 0 * * *") //매일 자정마다.
    public void expireCoupon() throws BaseException{
        try{
            couponDao.expireCoupon();

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
