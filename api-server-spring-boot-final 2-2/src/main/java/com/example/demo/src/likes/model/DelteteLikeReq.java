package com.example.demo.src.likes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DelteteLikeReq {
    private int userIdx;
    private int productIdx;
}
