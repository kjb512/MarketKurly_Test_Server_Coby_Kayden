package com.example.demo.src.deliveryInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PostDeliveyInfoReq {
    @NotNull
    private int userIdx;
    @NotEmpty
    private String address;
    @NotEmpty
    private String extraAddress;
}
