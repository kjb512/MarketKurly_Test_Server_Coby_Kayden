package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderDao orderDao;
    private final OrderProvider orderProvider;
    private final JwtService jwtService;

    public PostOrderRes createOrder(PostOrderReq postOrderReq) throws BaseException {
        try{
            int orderIdx = orderDao.createOrder(postOrderReq);
            return new PostOrderRes(orderIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackOn = BaseException.class)
    public void cancelOrder(int orderIdx) throws BaseException{
        String status = orderProvider.getOrderDeliveryStatus(orderIdx);
        if("DELIVERING".equals(status) || "DELIVERED".equals(status)){
            throw new BaseException(PATCH_CANCEL_ORDER_CHECK_STATUS);
        }
        try{
            int result = orderDao.cancelOrder(orderIdx);
            if(result == 0){
                throw new BaseException(DELETION_FAIL_ORDER);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
