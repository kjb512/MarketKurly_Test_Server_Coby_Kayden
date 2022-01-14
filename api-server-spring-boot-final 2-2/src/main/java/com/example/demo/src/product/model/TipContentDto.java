package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class TipContentDto {
    private int tipContentIdx;
    private String title;
    private String content;
    private String imageUrl;
}
