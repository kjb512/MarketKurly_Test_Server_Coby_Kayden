package com.example.demo.src.search;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.src.review.ReviewProvider;
import com.example.demo.src.review.ReviewService;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewReq;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.src.search.model.SearchDeleteRes;
import com.example.demo.src.search.model.SearchReq;
import com.example.demo.src.search.model.SearchRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.CHECK_NULL_SEARCH_KEYWORD;
import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/api/searches")
public class SearchController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SearchProvider searchProvider;
    @Autowired
    private final SearchService searchService;
    @Autowired
    private final JwtService jwtService;

    public SearchController(SearchProvider searchProvider, SearchService searchService, JwtService jwtService) {
        this.searchProvider = searchProvider;
        this.searchService = searchService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("/recent")
    public BaseResponse<List<SearchRes>> getRecentSearch(@RequestParam(required = false) int userIdx) {
        // Get Users
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<SearchRes> getRecentSearchRes = searchProvider.getRecentSearch(userIdx);
            return new BaseResponse<>(getRecentSearchRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @GetMapping("/recommend")
    public BaseResponse<List<SearchRes>> getRecentSearch(@RequestParam(required = false) String keyword) {
        // Get Users
        try{

            List<SearchRes> getRecentSearchRes = searchProvider.getRecommendSearch(keyword);
            return new BaseResponse<>(getRecentSearchRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<List<SearchRes>> addSearch(@RequestBody SearchReq searchReq) throws Exception {
        // Get Users
        try{
            if(searchReq.getKeyword().length()==0){
                return new BaseResponse<>(CHECK_NULL_SEARCH_KEYWORD);
            }
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(searchReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
             List<SearchRes> getRecentSearchRes = searchService.addSearch(searchReq.getUserIdx(),searchReq.getKeyword());
             return new BaseResponse<>(getRecentSearchRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("")
    public BaseResponse<SearchDeleteRes> deleteSearch(@RequestParam(required = false) int searchIdx) {
        // Get Users
        try{
            SearchDeleteRes getRecentSearchRes = searchService.deleteSearch(searchIdx);
            return new BaseResponse<>(getRecentSearchRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
