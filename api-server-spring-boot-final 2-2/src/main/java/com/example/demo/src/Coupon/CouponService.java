package com.example.demo.src.Coupon;

import com.example.demo.config.BaseException;
import com.example.demo.src.Coupon.model.PostCouponReq;
import com.example.demo.src.Coupon.model.PostCouponRes;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.DELETION_FAIL_Like;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponDao couponDao;
    private final CouponProvider couponProvider;
    private final JwtService jwtService;

    public PostCouponRes createCouponUser(PostCouponReq postCouponReq) throws BaseException {
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
}
