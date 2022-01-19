package com.example.demo.src.likes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetLikeRes {
    private int productIdx;
    private String profileImageUrl;
    private String title;
    private int price;
    private int discount;
}
