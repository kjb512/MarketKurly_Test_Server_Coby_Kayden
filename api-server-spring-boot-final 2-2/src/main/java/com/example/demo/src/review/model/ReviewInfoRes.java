package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ReviewInfoRes {
    private int productIdx;
    private int userIdx;
    private String userName;
    private String title;
    private String content;
    private String imageUrl;
    private Timestamp createAt;
    private String createDate;
}
