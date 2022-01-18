package com.example.demo.src.likes;


import com.example.demo.config.BaseException;
import com.example.demo.src.likes.model.DelteteLikeReq;
import com.example.demo.src.likes.model.GetLikeProductRes;
import com.example.demo.src.likes.model.PostLikeReq;
import com.example.demo.src.likes.model.PostLikeRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeDao likeDao;
    private final LikeProvider likeProvider;
    private final JwtService jwtService;

    @Transactional(rollbackOn = BaseException.class)
    public PostLikeRes createLike(PostLikeReq postLikeReq) throws BaseException{
        int status = likeDao.getLikeProduct(postLikeReq.getUserIdx(),postLikeReq.getProductIdx());
        if(status != 0 ){
            throw new BaseException(POST_LIKE_EXISTS);
        }
        try{
            int likeIdx = likeDao.createLike(postLikeReq);
            return new PostLikeRes(likeIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void cancelLike(DelteteLikeReq delteteLikeReq) throws BaseException{
        try{
            int result = likeDao.cancelLike(delteteLikeReq);
            if(result == 0){
                throw new BaseException(DELETION_FAIL_Like);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
