package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductInfoRes {
    private int productIdx;
    private String title;
    private String subTitle;
    private int price;
    private int discount;
    private String profileImgUrl;
    private String contentTitle;
    private String contentSubTitle;
    private String contentImageUrl;
    private String ingredientImageUrl;
    private String saleUnit;
    private String weight;
    private String deliveryType;
    private PackagingTypeDto packagingTypeDto;
    private String expirationDate;
    private String checkPointImgUrl;
    private String detailInfoImgUrl;
    private Date saleDeadLine;
    private String isKurlyOnly;
    private String isLimitQuantity;
    private int accumulate;
    private String origin;
    private List<AllergyDto> allergyDto;
    private BrandDto brandDto;
    private List<TipContentDto> tipContentDtos;
    private List<ProductGuideDto> productGuideDtos;
    // productGuide
}
