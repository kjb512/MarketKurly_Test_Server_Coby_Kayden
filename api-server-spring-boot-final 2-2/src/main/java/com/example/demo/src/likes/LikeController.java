package com.example.demo.src.likes;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.Constant;
import com.example.demo.src.likes.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeProvider likeProvider;
    private final LikeService likeService;
    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<PostLikeRes> createLike(@Validated @RequestBody PostLikeReq postLikeReq, Errors errors) {
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
            if(postLikeReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            PostLikeRes postLikeRes = likeService.createLike(postLikeReq);
            return new BaseResponse<>(postLikeRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}")
    public BaseResponse<List<GetLikeRes>> getLike(@PathVariable int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetLikeRes> getLikeRes = likeProvider.getLike(userIdx);
            return new BaseResponse<>(getLikeRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}/counts")
    public BaseResponse<GetLikeCountRes> getLikeCount(@PathVariable int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            GetLikeCountRes getLikeCountRes = likeProvider.getLikeCount(userIdx);
            return new BaseResponse<>(getLikeCountRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/users/{userIdx}/{productIdx}")
    public BaseResponse<GetLikeProductRes> getLikeProduct(@PathVariable int userIdx, @PathVariable int productIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            GetLikeProductRes getLikeProductRes = likeProvider.getLikeProduct(userIdx, productIdx);
            return new BaseResponse<>(getLikeProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> cancelLike(@Validated @RequestBody DelteteLikeReq delteteLikeReq, Errors errors){
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인

            if(delteteLikeReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            likeService.cancelLike(delteteLikeReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
