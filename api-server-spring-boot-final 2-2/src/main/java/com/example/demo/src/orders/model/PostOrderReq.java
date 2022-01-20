package com.example.demo.src.orders.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    @NotNull
    private int userIdx;
    @NotNull
    private int cartIdx;
    private int couponUserIdx;
    @NotNull
    private int paymentType;
    @NotNull
    private int productPrice;
    @NotNull
    private int discountPrice;
    @NotNull
    private int deliveryPrice;
    @NotNull
    private int couponDiscount;
    @NotNull
    private int rewardDiscount;
    @NotNull
    private int amountOfPayment;
}
