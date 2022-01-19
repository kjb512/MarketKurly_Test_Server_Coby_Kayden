package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.mail.Multipart;

@Getter
@Setter
public class ReviewDto {
    private int productIdx;
    private int userIdx;
    private String title;
    private String content;
}
