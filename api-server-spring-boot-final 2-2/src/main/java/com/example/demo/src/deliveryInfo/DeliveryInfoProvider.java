package com.example.demo.src.deliveryInfo;

import com.example.demo.config.BaseException;
import com.example.demo.src.deliveryInfo.model.GetDeliveryInfoRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class DeliveryInfoProvider {

    private final DeliveryInfoDao deliveryInfoDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<GetDeliveryInfoRes> getDeliveryInfo(int userIdx) throws BaseException {
        try{
            List<GetDeliveryInfoRes> getDeliveryInfoRes = deliveryInfoDao.getDeliveryInfo(userIdx);
            return getDeliveryInfoRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int getUserIdx(int deliveryInfoIdx) throws BaseException {
        try {
            int userIdx = deliveryInfoDao.getUserIdx(deliveryInfoIdx);
            return userIdx;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
