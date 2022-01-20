package com.example.demo.src.search.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchDeleteRes {
    private int searchIdx;
    private String keyword;
    private String status;
}
