package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDeliveryInfoRes {
    private int deliveryInfoIdx;
    private String isDefaultAddress;
    private String address;
    private String receiver;
    private String receiverPhone;
    private String deliveryType;
}
