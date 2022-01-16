package com.example.demo.src.cart.model;


import com.example.demo.src.product.model.ProductMiniInfoRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartUserInfo {
    private int cartIdx;
    private String deliveryType;
    private String address;
    private String subAddress;
}
