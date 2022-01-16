package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class PostDeliveyInfoReq {
    @NotEmpty
    private int userIdx;
    @NotEmpty
    private String address;
    @NotEmpty
    private String extraAddress;
}
