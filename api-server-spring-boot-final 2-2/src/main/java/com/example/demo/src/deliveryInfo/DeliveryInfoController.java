package com.example.demo.src.deliveryInfo;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.deliveryInfo.model.GetDeliveryInfoRes;
import com.example.demo.src.deliveryInfo.model.PatchDeliveryInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RequiredArgsConstructor
@RequestMapping("/api/delivery-info")
@RestController
public class DeliveryInfoController {

    // 생성자로 의존성 주입
    private final DeliveryInfoProvider deliveryInfoProvider;
    private final DeliveryInfoService deliveryInfoService;
    private final JwtService jwtService;


    @PostMapping("")
    public BaseResponse<PostDeliveyInfoRes> createDeliveryInfo(@Validated @RequestBody PostDeliveyInfoReq postDeliveyInfoReq, Errors errors, @RequestParam(required = false) String isDefaultAddress) {
        //TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        //Validation
        if(errors.hasErrors()){
            // validation과 정규식은 PostUserReq에서 Validator 사용
            // validation 에러 메세지 처리
            return new BaseResponse<>(Constant.refineErrors(errors));
        }

        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(postDeliveyInfoReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            PostDeliveyInfoRes postDeliveyInfoRes = deliveryInfoService.createDeliveryInfo(postDeliveyInfoReq, isDefaultAddress);
            return new BaseResponse<>(postDeliveyInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{userIdx}")
    public BaseResponse<List<GetDeliveryInfoRes>> getDeliveryInfo(@PathVariable("userIdx") int  userIdx) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            // Get Users
            List<GetDeliveryInfoRes> getDeliveryInfoRes = deliveryInfoProvider.getDeliveryInfo(userIdx);
            return new BaseResponse<>(getDeliveryInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/{deliveryInfoIdx}")
    public BaseResponse<String> modifyUserName(@PathVariable("deliveryInfoIdx") int deliveryInfoIdx, @RequestBody PatchDeliveryInfoReq patchDeliveryInfoReq, @RequestParam(required = false) String isDefaultAddress){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            int userIdxByDeliveryInfo = deliveryInfoProvider.getUserIdx(deliveryInfoIdx);
            if(userIdxByDeliveryInfo != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            deliveryInfoService.modifyDeliveryInfo(deliveryInfoIdx, patchDeliveryInfoReq, isDefaultAddress);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
