package com.example.demo.src.cart;


import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.CartFromUserRes;
import com.example.demo.src.cart.model.CartUserInfo;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoForCartRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class CartProvider {

    private final CartDao cartDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CartProvider(CartDao cartDao, JwtService jwtService) {
        this.cartDao = cartDao;
        this.jwtService = jwtService;
    }

    public CartFromUserRes getCartFromUser(int userIdx) throws BaseException{
        try{
            if(cartDao.checkCartFromUser(userIdx)==0){
                throw new BaseException(USER_CART_NOT_EXIST);
            }

            CartUserInfo cartUserInfo;
            cartUserInfo = cartDao.getCartUserInfo(userIdx);
            CartFromUserRes cartFromUserRes = new CartFromUserRes(cartUserInfo.getCartIdx(),
                    calculateDiscount(cartDao.getUserCartByPackagingType(userIdx,1)),
                    calculateDiscount(cartDao.getUserCartByPackagingType(userIdx,2)),
                    calculateDiscount(cartDao.getUserCartByPackagingType(userIdx,3)),
                    cartUserInfo.getAddress(),
                    cartUserInfo.getSubAddress(),
                    cartUserInfo.getDeliveryType());

            return cartFromUserRes;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<ProductMiniInfoForCartRes> calculateDiscount(List<ProductMiniInfoForCartRes> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setDiscountAfterPrice(list.get(i).getPrice()*(100-list.get(i).getDiscount())/100);
        }
        return list;
    }
}
