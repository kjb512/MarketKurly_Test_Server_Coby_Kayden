package com.example.demo.src.search.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchReq {
    private int userIdx;
    private String keyword;
}
