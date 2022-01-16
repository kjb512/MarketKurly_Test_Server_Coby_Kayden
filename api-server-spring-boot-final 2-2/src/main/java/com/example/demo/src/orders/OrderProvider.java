package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.src.orders.model.GetOrderProductRes;
import com.example.demo.src.orders.model.GetOrderRes;
import com.example.demo.src.orders.model.GetOrdersRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class OrderProvider {

    private final OrderDao orderDao;
    private final JwtService jwtService;

    public List<GetOrdersRes> getOrdersByUser(int userIdx) throws BaseException {
        try{
            List<GetOrdersRes> getOrderRes = orderDao.getOrdersByUser(userIdx);
            return getOrderRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetOrderProductRes> getOrderProduct(int orderIdx) throws BaseException {
        try{
            List<GetOrderProductRes> getOrderProductRes = orderDao.getOrderProduct(orderIdx);
            return getOrderProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int getUserIdx(int orderIdx) throws BaseException {
        try {
            int userIdx = orderDao.getUserIdx(orderIdx);
            return userIdx;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetOrderRes getOrder(int orderIdx) throws BaseException {
        try {
            GetOrderRes getOrderRes = orderDao.getOrder(orderIdx);
            return getOrderRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public String getOrderDeliveryStatus(int orderIdx) throws BaseException {
        try {
            String status = orderDao.getOrderDeliveryStatus(orderIdx);
            return status;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
