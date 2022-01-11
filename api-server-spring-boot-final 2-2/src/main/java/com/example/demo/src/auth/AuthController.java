package com.example.demo.src.auth;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.auth.model.GetAuthReq;
import com.example.demo.src.auth.model.GetAuthRes;
import com.example.demo.src.auth.model.GetIdReq;
import com.example.demo.src.auth.model.GetIdRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final JwtService jwtService;


    //핸드폰 인증
    @GetMapping("/phone")
    public BaseResponse<GetAuthRes> authPhone (@Validated @RequestBody GetAuthReq getAuthReq, Errors errors) {
        //Validation
        if (errors.hasErrors()) {
            // validation과 정규식은 PostUserReq에서 Validator 사용
            // validation 에러 메세지 처리
            return new BaseResponse<>(Constant.refineErrors(errors));
        }

        try {
            GetAuthRes getAuthRes = authService.sendSms(getAuthReq);
            return new BaseResponse<>(getAuthRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // id 중복 확인
    @GetMapping("/id")
    public BaseResponse<GetIdRes> authId(@Validated @RequestBody GetIdReq getIdReq, Errors errors)  {
        //Validation
        if (errors.hasErrors()) {
            // validation과 정규식은 PostUserReq에서 Validator 사용
            // validation 에러 메세지 처리
            return new BaseResponse<>(Constant.refineErrors(errors));
        }
        try {
            GetIdRes getAuthRes = authService.doubleCheckId(getIdReq);
            return new BaseResponse<>(getAuthRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }
}
