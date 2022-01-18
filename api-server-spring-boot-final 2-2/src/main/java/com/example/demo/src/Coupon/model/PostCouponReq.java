package com.example.demo.src.Coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCouponReq {

    private int userIdx;
    private int couponIdx;
}
