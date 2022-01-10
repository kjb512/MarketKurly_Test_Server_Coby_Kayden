package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private int productIdx;
    private String name;
    private String subTitle;
    private int price;
    private int discount;
    private String profileUrl;
    private String contentTitle;
    private String contentSubTitle;
    private String contentImageUrl;
    private String ingredientImageUrl;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String saleUnit;
    private String weight;
    private String delivery;
    private String packagingType;
    private String expirationDate;
    private String guidance;
    private String allergy;
    private String origin;
    // subCidx
    private String checkPointImageUrl;
    // brandIdx
    private String gift;
    private String saleDeadLine;
    private String kurlyOnly;
    private String limitQuantity;
    private String accumulate;
    private String detailInfoImageUrl;
}
