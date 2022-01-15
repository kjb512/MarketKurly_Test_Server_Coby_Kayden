package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchDeliveryInfoReq {
    private String extraAddress;
    private String receiver;
    private String receiverPhone;
}
