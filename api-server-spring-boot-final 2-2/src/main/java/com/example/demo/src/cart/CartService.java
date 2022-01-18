package com.example.demo.src.cart;


import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.CartFromUserRes;
import com.example.demo.src.product.ProductDao;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

// Service Create, Update, Delete 의 로직 처리

@Service
public class CartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartDao cartDao;
    private final ProductDao productDao;
    private final CartProvider cartProvider;
    private final JwtService jwtService;

    @Autowired
    public CartService(CartDao cartDao, CartProvider cartProvider, ProductDao productDao,JwtService jwtService) {
        this.cartDao = cartDao;
        this.cartProvider = cartProvider;
        this.productDao = productDao;
        this.jwtService = jwtService;
    }

    // Create userCart
    public void createUserCart(int userIdx, int deliveryInfoIdx) throws BaseException {
        try{
            cartDao.createUserCart(userIdx, deliveryInfoIdx);
        }
        catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //TODO void -> cartRes 변경
    public CartFromUserRes addProductInCart(int productIdx, int userIdx) throws BaseException{
        try{
            if(cartDao.checkCartFromUser(userIdx)==0){
                throw new BaseException(USER_CART_NOT_EXIST);
            }

            if(productDao.checkProductIdx(productIdx)==0){
                throw new BaseException(GET_PRODUCT_IDX_NOT_EXIST);
            }

            if(cartDao.checkProductInCartForAdd(productIdx,userIdx)==0){
                cartDao.addProductInCart(productIdx,userIdx);
            }
            else if(cartDao.checkProductInCartForAdd(productIdx,userIdx)==1){
                cartDao.addProductCount(productIdx,userIdx);
            }
            return cartProvider.getCartFromUser(userIdx);
        }
        catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public CartFromUserRes deleteProductInCartOne(int productIdx, int userIdx) throws BaseException{

        try{
            if(cartDao.checkCartFromUser(userIdx)==0){
                throw new BaseException(USER_CART_NOT_EXIST);
            }

            if(productDao.checkProductIdx(productIdx)==0){
                throw new BaseException(GET_PRODUCT_IDX_NOT_EXIST);
            }

            cartDao.deleteProductInCart(productIdx,userIdx);
            return cartProvider.getCartFromUser(userIdx);
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public CartFromUserRes deleteProductInCartAll(int productIdx, int userIdx) throws BaseException{
        try{
            if(cartDao.checkCartFromUser(userIdx)==0){
                throw new BaseException(USER_CART_NOT_EXIST);
            }

            if(productDao.checkProductIdx(productIdx)==0){
                throw new BaseException(GET_PRODUCT_IDX_NOT_EXIST);
            }

            cartDao.deleteProductInCartAll(productIdx,userIdx);
            return cartProvider.getCartFromUser(userIdx);
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }




    // Delete userCart - status 변경



}
