package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.CartDao;
import com.example.demo.src.cart.CartProvider;
import com.example.demo.src.cart.CartService;
import com.example.demo.src.coupons.CouponService;
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
    private final CouponService couponService;
    private final JwtService jwtService;

    @Transactional(rollbackOn = BaseException.class)
    public PostOrderRes createOrder(PostOrderReq postOrderReq) throws BaseException {
        try{
            //주문하기
            int orderIdx = orderDao.createOrder(postOrderReq);
            //기존 카트 지우기
            cartDao.deleteCart(postOrderReq.getCartIdx());
            // 배송지 가져오기
            int deliveryInfoIdx = userProvider.getDeliveryInfoByUser(postOrderReq.getUserIdx());
            //카트 재생성
            int cartIdx = cartService.createUserCart(postOrderReq.getUserIdx(), deliveryInfoIdx);
            //사용한 쿠폰 지우기
            if(postOrderReq.getCouponUserIdx() != 0 ){
                couponService.cancelCouponUser(postOrderReq.getUserIdx(),postOrderReq.getCouponUserIdx());
            }
            return new PostOrderRes(orderIdx,cartIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackOn = BaseException.class)
    public void cancelOrder(int orderIdx) throws BaseException{
        // 배송상태를 가져옴
        String status = orderProvider.getOrderDeliveryStatus(orderIdx);
        // 배송중, 배송됨의 경우 취소 불가
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
