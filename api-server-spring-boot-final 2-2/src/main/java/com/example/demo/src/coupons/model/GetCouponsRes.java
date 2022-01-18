package com.example.demo.src.coupons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCouponsRes {
    private int discount;
    private String name;
    private String deadLine;
    private String status;
}
