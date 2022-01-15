package com.example.demo.src.deliveryInfo;

import com.example.demo.config.BaseException;
import com.example.demo.src.deliveryInfo.model.PatchDeliveryInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class DeliveryInfoService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DeliveryInfoDao deliveryInfoDao;
    private final DeliveryInfoProvider deliveryInfoProvider;
    private final JwtService jwtService;

    public PostDeliveyInfoRes createDeliveryInfo(PostDeliveyInfoReq postDeliveyInfoReq) throws BaseException {
        try{
            int deliveryInfoIdx = deliveryInfoDao.createDeliveryInfo(postDeliveyInfoReq);

            return new PostDeliveyInfoRes(deliveryInfoIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyDeliveryInfo(int userIdx, PatchDeliveryInfoReq patchDeliveryInfoReq) throws BaseException {
        try{
            int result = deliveryInfoDao.modifyUserName(userIdx, patchDeliveryInfoReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_DELIVERYINFO);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
