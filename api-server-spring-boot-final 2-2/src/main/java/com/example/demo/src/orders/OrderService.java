package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

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
}
