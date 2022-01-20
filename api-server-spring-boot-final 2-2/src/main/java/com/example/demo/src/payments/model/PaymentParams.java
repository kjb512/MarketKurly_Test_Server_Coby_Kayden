package com.example.demo.src.payments.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentParams {
    private String paymentKey;
    private String status;


    PaymentParams(String paymentKey, String status){
        this.paymentKey = paymentKey;
        this.status = status;
    }

    PaymentParams(){

    }

    public String getPaymentKey(){
        return paymentKey;
    }

    public void setPaymentKey(String paymentKey){
        this.paymentKey = paymentKey;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
