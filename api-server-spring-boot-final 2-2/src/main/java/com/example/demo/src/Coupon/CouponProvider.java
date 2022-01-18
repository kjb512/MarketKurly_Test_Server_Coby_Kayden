package com.example.demo.src.Coupon;

import com.example.demo.config.BaseException;
import com.example.demo.src.Coupon.model.GetCouponsRes;
import com.example.demo.src.orders.model.GetOrdersRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class CouponProvider {

    private final CouponDao couponDao;
    private final JwtService jwtService;

    public List<GetCouponsRes> getCouponsByUser(int userIdx) throws BaseException {
        try{
            List<GetCouponsRes> getCouponsRes = couponDao.getCouponsByUser(userIdx);
            return getCouponsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCouponsRes> getAvailabeCoupons(int userIdx, int cartIdx) throws BaseException{
        try{
            List<GetCouponsRes> getCouponsRes = couponDao.getAvailabeCoupons(userIdx, cartIdx);
            return getCouponsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int doubleCheckCoupon(int userIdx, int couponIdx) throws BaseException{
        try{
            return couponDao.doubleCheckCoupon(userIdx, couponIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
