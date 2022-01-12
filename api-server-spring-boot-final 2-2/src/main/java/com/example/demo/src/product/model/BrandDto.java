package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class BrandDto {
    private int brandIdx;
    private String brandInfo;
    private String contentImageUrl1;
    private String contentImageUrl2;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String status;
}
