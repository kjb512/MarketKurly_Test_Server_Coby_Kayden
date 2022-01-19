package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrdersOftenRes {
    private String profileImageUrl;
    private int productIdx;
    private String title;
    private int price;
    private int count;

}
