package com.example.demo.src.payments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPaymentReq {
    private String amount;
    private String orderId;
    private String cardNumber;
    private String cardExpirationYear;
    private String cardExpirationMonth;
    private String orderName;
    private String cardPassword;
    private String customerBirthday;
}
