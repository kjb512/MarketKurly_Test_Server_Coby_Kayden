package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.orders.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProvider orderProvider;
    private final OrderService orderService;
    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<PostOrderRes> createOrder(@Validated @RequestBody PostOrderReq postOrderReq, Errors errors) {
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
            if(postOrderReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            PostOrderRes postOrderRes = orderService.createOrder(postOrderReq);
            return new BaseResponse<>(postOrderRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{orderIdx}/products")
    public BaseResponse<List<GetOrderProductRes>> getOrderProduct(@PathVariable int orderIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            int userIdxByOrder = orderProvider.getUserIdx(orderIdx);
            if(userIdxByOrder != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetOrderProductRes> getOrderProductRes = orderProvider.getOrderProduct(orderIdx);
            return new BaseResponse<>(getOrderProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{orderIdx}")
    public BaseResponse<GetOrderRes> getOrder(@PathVariable int orderIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            int userIdxByOrder = orderProvider.getUserIdx(orderIdx);
            if(userIdxByOrder != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            GetOrderRes getOrderRes = orderProvider.getOrder(orderIdx);
            return new BaseResponse<>(getOrderRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}")
    public BaseResponse<List<GetOrdersRes>> getOrdersByUser(@PathVariable int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetOrdersRes> getOrderRes = orderProvider.getOrdersByUser(userIdx);
            return new BaseResponse<>(getOrderRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}/often-products")
    public BaseResponse<List<GetOrdersOftenRes>> getOrdersOften(@PathVariable int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetOrdersOftenRes> getOrdersOftenRes = orderProvider.getOrdersOften(userIdx);
            return new BaseResponse<>(getOrdersOftenRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/deletion/{orderIdx}")
    public BaseResponse<String> cancelOrder(@PathVariable("orderIdx") int orderIdx){
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            int userIdxByOrder = orderProvider.getUserIdx(orderIdx);
            if(userIdxByOrder != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            orderService.cancelOrder(orderIdx);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
