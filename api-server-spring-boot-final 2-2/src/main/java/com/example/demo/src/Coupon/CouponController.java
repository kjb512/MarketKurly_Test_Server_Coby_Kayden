package com.example.demo.src.Coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.Coupon.model.GetCouponsRes;
import com.example.demo.src.Coupon.model.PostCouponReq;
import com.example.demo.src.Coupon.model.PostCouponRes;
import com.example.demo.src.likes.model.DelteteLikeReq;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;
    private final CouponProvider couponProvider;
    private final JwtService jwtService;


    // 쿠폰 발급받기
    @PostMapping("")
    public BaseResponse<PostCouponRes> createCouponUser(@Validated @RequestBody PostCouponReq postCouponReq, Errors errors) {
        //TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        //Validation
        if(errors.hasErrors()){
            // validation과 정규식은 PostUserReq에서 Validator 사용
            // validation 에러 메세지 처리
            return new BaseResponse<>(Constant.refineErrors(errors));
        }

        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(postCouponReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            PostCouponRes postCouponRes = couponService.createCouponUser(postCouponReq);
            return new BaseResponse<>(postCouponRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}")
    public BaseResponse<List<GetCouponsRes>> getCouponsByUser(@PathVariable int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetCouponsRes> getCouponsRes = couponProvider.getCouponsByUser(userIdx);
            return new BaseResponse<>(getCouponsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}/{cartIdx}")
    public BaseResponse<List<GetCouponsRes>> getAvailabeCoupons(@PathVariable int userIdx, @PathVariable int cartIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetCouponsRes> getCouponsRes = couponProvider.getAvailabeCoupons(userIdx, cartIdx);
            return new BaseResponse<>(getCouponsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("users/{userIdx}/{couponUserIdx}")
    public BaseResponse<String> cancelCouponUser(@PathVariable int userIdx, @PathVariable int couponUserIdx) {
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인

            if (userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            couponService.cancelCouponUser(userIdx, couponUserIdx);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
