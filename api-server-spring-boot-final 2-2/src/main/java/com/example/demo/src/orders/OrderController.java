package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.users.model.PostUserReq;
import com.example.demo.src.users.model.PostUserRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProvider orderProvider;
    private final OrderService orderService;
    private final JwtService jwtService;

//    @PostMapping("")
//    public BaseResponse<> createOrder(@Validated @RequestBody  , Errors errors) {
//        //TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
//
//        //Validation
//        if(errors.hasErrors()){
//            // validation과 정규식은 PostUserReq에서 Validator 사용
//            // validation 에러 메세지 처리
//            return new BaseResponse<>(Constant.refineErrors(errors));
//        }
//
////        try{
////             // = .createUser();
////            //return new BaseResponse<>();
////        } catch(BaseException exception){
////            return new BaseResponse<>((exception.getStatus()));
////        }
//        return ;
//    }
}
