package com.example.demo.src.likes;

import com.example.demo.config.BaseException;
import com.example.demo.src.likes.model.GetLikeCountRes;
import com.example.demo.src.likes.model.GetLikeProductRes;
import com.example.demo.src.likes.model.GetLikeRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class LikeProvider {

    private final LikeDao likeDao;
    private final JwtService jwtService;

    public List<GetLikeRes> getLike(int userIdx) throws BaseException{
        try {
            List<GetLikeRes> getLikeRes = likeDao.getLike(userIdx);
            return getLikeRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetLikeCountRes getLikeCount(int userIdx) throws BaseException{
        try {
            GetLikeCountRes getLikeCountRes = likeDao.getLikeCount(userIdx);
            return getLikeCountRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetLikeProductRes getLikeProduct(int userIdx, int prductIdx) throws BaseException{
        try {
            int likeIdx = likeDao.getLikeProduct(userIdx, prductIdx);
            if(likeIdx>0){
                return new GetLikeProductRes("T");
            }else{
                return new GetLikeProductRes("F");
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
