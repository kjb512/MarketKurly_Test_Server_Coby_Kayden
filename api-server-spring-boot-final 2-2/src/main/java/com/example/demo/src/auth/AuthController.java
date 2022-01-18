package com.example.demo.src.auth;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.auth.model.GetAuthReq;
import com.example.demo.src.auth.model.GetAuthRes;
import com.example.demo.src.auth.model.GetIdReq;
import com.example.demo.src.auth.model.GetIdRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.ValidationRegex;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.CHECK_INVALID_PHONE;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final JwtService jwtService;


    //핸드폰 인증
    @GetMapping("/phone")
    public BaseResponse<GetAuthRes> authPhone (@RequestParam String phone) {
        //Validation
//        if (errors.hasErrors()) {
//            // validation과 정규식은 PostUserReq에서 Validator 사용
//            // validation 에러 메세지 처리
//            return new BaseResponse<>(Constant.refineErrors(errors));
//        }
        if(!ValidationRegex.isRegexPhone(phone)){
            return new BaseResponse<>(CHECK_INVALID_PHONE);
        }

        try {
            GetAuthRes getAuthRes = authService.sendSms(phone);
            return new BaseResponse<>(getAuthRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
