package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetOrdersRes {
    private int orderIdx;
    private String title;
    private int type;
    private int cases;
    private String paymentDate;
    private String paymentType;
    private int amountOfPayment;
    private String deliveryStatus;
}
