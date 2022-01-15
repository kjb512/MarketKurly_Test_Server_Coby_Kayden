package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDeliveyInfoReq {
    private int userIdx;
    private String address;
    private String extraAddress;
}
