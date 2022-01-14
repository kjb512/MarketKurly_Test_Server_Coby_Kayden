package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.product.ProductService;
import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.product.model.ProductMiniInfoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class CartController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CartProvider cartProvider;
    @Autowired
    private final CartService cartService;
    @Autowired
    private final JwtService jwtService;

    public CartController(CartProvider cartProvider, CartService cartService, JwtService jwtService){
        this.cartProvider = cartProvider;
        this.cartService = cartService;
        this.jwtService = jwtService;
    }

}
