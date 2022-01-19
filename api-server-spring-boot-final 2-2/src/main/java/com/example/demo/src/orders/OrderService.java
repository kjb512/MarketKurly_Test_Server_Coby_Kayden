package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.CartDao;
import com.example.demo.src.cart.CartProvider;
import com.example.demo.src.cart.CartService;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.src.users.UserProvider;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final CartService cartService;
    private final CartDao cartDao;
    private final OrderDao orderDao;
    private final UserProvider userProvider;
    private final OrderProvider orderProvider;
    private final JwtService jwtService;

    @Transactional(rollbackOn = BaseException.class)
    public PostOrderRes createOrder(PostOrderReq postOrderReq) throws BaseException {
        try{
            int orderIdx = orderDao.createOrder(postOrderReq);
            cartDao.deleteCart(postOrderReq.getCartIdx());
            int deliveryInfoIdx = userProvider.getDeliveryInfoByUser(postOrderReq.getUserIdx());
            int cartIdx = cartService.createUserCart(postOrderReq.getUserIdx(), deliveryInfoIdx);
            return new PostOrderRes(orderIdx,cartIdx);
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
