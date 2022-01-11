package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductInfoRes {
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
    private String saleUnit;
    private String weight;
    private String delivery;
    private String packagingType;
    private String expirationDate;
    private String guidance;
    private String allergy;
    private String origin;
    private BrandDto brandDto;
    private TipContentDto tipContentDto;
}
