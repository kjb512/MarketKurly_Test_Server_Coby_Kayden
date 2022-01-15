package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDeliveryInfoRes {
    private String adress;
    private   String receiver;
    private String receiverPhone;
    private String deliveryType;
}
