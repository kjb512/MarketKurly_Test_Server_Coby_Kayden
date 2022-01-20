package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// product List 출력에 쓰이는 데이터 모델

@Getter
@Setter
@AllArgsConstructor
public class ProductMiniInfoForCartRes {
    // title 사진 가격 할인 후 가격 개수
    private int productIdx;
    private String title;
    private String profileImgUrl;
    private int price;
    private int discount;
    private int discountAfterPrice;
    private int productCount;
}
