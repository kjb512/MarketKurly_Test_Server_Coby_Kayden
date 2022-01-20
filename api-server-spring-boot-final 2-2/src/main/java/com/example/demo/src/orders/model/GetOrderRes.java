package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private int prouctPrice;
    private int deliveryPrice;
    private int discountPrice;
    private int couponDiscount;
    private int rewardDiscount;
    private int amountOfPayment;
    private int orderIdx;
    private String userName;
    private String paymentDate;
    private String receiver;
    private String receiverPhone;
    private String deliveryType;
    private String address;
    private String afterMessageDeliveryTime;
}
