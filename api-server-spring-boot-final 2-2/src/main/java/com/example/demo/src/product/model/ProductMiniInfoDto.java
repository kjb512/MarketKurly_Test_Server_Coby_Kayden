package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// product List 출력에 쓰이는 데이터 모델

@Getter
@Setter
@AllArgsConstructor
public class ProductMiniInfoDto {
    private int productIdx;
    private String title;
    private String profileImgUrl;
    private Boolean isKurlyOnly;
    private int price;
    private int discount;
}
