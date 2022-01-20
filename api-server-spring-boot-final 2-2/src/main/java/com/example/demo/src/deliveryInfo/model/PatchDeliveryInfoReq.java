package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class PatchDeliveryInfoReq {
    @NotEmpty
    private String extraAddress;
    @NotEmpty
    private String receiver;
    @NotEmpty
    private String receiverPhone;
}
