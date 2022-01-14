package com.example.demo.src.cart;


import com.example.demo.config.BaseException;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.model.ProductInfoRes;
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



}
