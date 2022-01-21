package com.example.demo.src.search;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.ReviewDao;
import com.example.demo.src.review.ReviewProvider;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewReq;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.src.search.model.SearchDeleteRes;
import com.example.demo.src.search.model.SearchRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.CHECK_INVALID_SEARCH_KEYWORD;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

// Service Create, Update, Delete 의 로직 처리
@Service
public class SearchService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SearchDao searchDao;
    private final SearchProvider searchProvider;
    private final JwtService jwtService;


    @Autowired
    public SearchService(SearchDao searchDao, SearchProvider searchProvider, JwtService jwtService) {
        this.searchDao = searchDao;
        this.searchProvider = searchProvider;
        this.jwtService = jwtService;
    }

    public List<SearchRes> addSearch(int userIdx, String keyword) throws Exception{
        try{
            searchDao.addSearch(userIdx,keyword);
            List<SearchRes> searchRes = searchProvider.getRecentSearch(userIdx);
            return searchRes;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public SearchDeleteRes deleteSearch(int searchIdx) throws BaseException {
        try {
            SearchDeleteRes searchDeleteRes = searchDao.deleteSearch(searchIdx);
            return searchDeleteRes;
        }catch (Exception exception){
            throw new BaseException(CHECK_INVALID_SEARCH_KEYWORD);
        }
    }
}
