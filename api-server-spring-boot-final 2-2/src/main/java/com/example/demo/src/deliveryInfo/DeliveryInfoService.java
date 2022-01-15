package com.example.demo.src.deliveryInfo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.deliveryInfo.model.PatchDeliveryInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class DeliveryInfoService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DeliveryInfoDao deliveryInfoDao;
    private final DeliveryInfoProvider deliveryInfoProvider;
    private final JwtService jwtService;

    @Transactional(rollbackOn = BaseException.class)
    public PostDeliveyInfoRes createDeliveryInfo(PostDeliveyInfoReq postDeliveyInfoReq, String isDefaultAddress) throws BaseException {
        try{
            if("T".equals(isDefaultAddress)){
                deliveryInfoDao.modifyDefaultAddress(postDeliveyInfoReq.getUserIdx());
                int deliveryInfoIdx  = deliveryInfoDao.createDeliveryInfoWithDefault(postDeliveyInfoReq, isDefaultAddress);
                return new PostDeliveyInfoRes(deliveryInfoIdx);
            }
            int deliveryInfoIdx = deliveryInfoDao.createDeliveryInfo(postDeliveyInfoReq);

            return new PostDeliveyInfoRes(deliveryInfoIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackOn = BaseException.class)
    public void modifyDeliveryInfo(int deliveryInfoIdx, PatchDeliveryInfoReq patchDeliveryInfoReq, String isDefaultAddress) throws BaseException {
        try{
            if("T".equals(isDefaultAddress)){
                deliveryInfoDao.modifyDefaultAddress(deliveryInfoIdx);
                int result = deliveryInfoDao.modifyUserNameWithDefault(deliveryInfoIdx, patchDeliveryInfoReq,isDefaultAddress);
                if(result == 0){
                    throw new BaseException(MODIFY_FAIL_DELIVERYINFO);
                }
            }
            else{
                int result = deliveryInfoDao.modifyUserName(deliveryInfoIdx, patchDeliveryInfoReq);
                if(result == 0){
                    throw new BaseException(MODIFY_FAIL_DELIVERYINFO);
                }
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
