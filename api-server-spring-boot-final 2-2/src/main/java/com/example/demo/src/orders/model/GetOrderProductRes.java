package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderProductRes {
    private int productIdx;
    private String title;
    private int price;
    private int discount;
    private int count;
    private String packagingType;
}
