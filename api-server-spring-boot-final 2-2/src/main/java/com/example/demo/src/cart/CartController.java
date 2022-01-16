package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cart.model.CartFromUserRes;
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
@RequestMapping("/api/carts")
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

    // 조회
    @ResponseBody
    @GetMapping("")
    public BaseResponse<CartFromUserRes> getCartByUserIdx(@RequestParam(required = false) int userIdx ) {
        // Get Users
        try{
            CartFromUserRes cartFromUserRes = cartProvider.getCartFromUser(userIdx);
            return new BaseResponse<>(cartFromUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    // 생성
    @ResponseBody
    @PostMapping("")
    public BaseResponse<CartFromUserRes> addProductInCart(@RequestParam(required = false) int userIdx, @RequestParam(required = false) int productIdx ) {
        try{
            CartFromUserRes cartFromUserRes = cartService.addProductInCart(productIdx,userIdx);
            return new BaseResponse<>(cartFromUserRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 삭제
    @ResponseBody
    @PatchMapping("")
    public BaseResponse<CartFromUserRes> deleteProductOneInCart(@RequestParam(required = false) int userIdx, @RequestParam(required = false) int productIdx ) {
        try{
            CartFromUserRes cartFromUserRes = cartService.deleteProductInCartOne(productIdx,userIdx);
            return new BaseResponse<>(cartFromUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/all")
    public BaseResponse<CartFromUserRes> deleteProductAllInCart(@RequestParam(required = false) int userIdx, @RequestParam(required = false) int productIdx ) {
        // Get Users
        try{
            CartFromUserRes cartFromUserRes = cartService.deleteProductInCartAll(productIdx,userIdx);
            return new BaseResponse<>(cartFromUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //전체 삭제

}
