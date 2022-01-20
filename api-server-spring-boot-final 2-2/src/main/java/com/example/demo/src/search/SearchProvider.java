package com.example.demo.src.search;


import com.example.demo.config.BaseException;
import com.example.demo.src.review.ReviewDao;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.src.search.model.SearchRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class SearchProvider {

    private final SearchDao searchDao;
    private final JwtService jwtService;

    Calendar cal = Calendar.getInstance();

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearchProvider(SearchDao searchDao, JwtService jwtService) {
        this.searchDao = searchDao;
        this.jwtService = jwtService;
    }

    public List<SearchRes> getRecentSearch(int userIdx) throws BaseException {
        try{
            List<SearchRes> getRecentSearch = searchDao.getRecentSearch(userIdx);
            return getRecentSearch;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<SearchRes> getRecommendSearch(String keyword) throws BaseException{
        try {
            List<SearchRes> getRecommendSearch = searchDao.getSearchRecommend(keyword);
            return getRecommendSearch;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
