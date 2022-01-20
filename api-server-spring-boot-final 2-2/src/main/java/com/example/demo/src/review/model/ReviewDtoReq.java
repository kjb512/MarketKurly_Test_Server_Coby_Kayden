package com.example.demo.src.review.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDtoReq {
    private int productIdx;
    private String title;
    private String content;
}
