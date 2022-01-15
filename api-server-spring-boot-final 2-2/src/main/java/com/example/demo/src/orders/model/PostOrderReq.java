package com.example.demo.src.orders.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    private int userIdx;
    private int cartIdx;
    private int paymentType;
    private int deliveryInfoIdx;
    private int productPrice;
    private int discountPrice;
    private int deliveryPrice;
    private int couponDiscount;
    private int rewardDiscount;
    private int amountOfPayment;
}
