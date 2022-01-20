package com.example.demo.src.search.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class SearchRes {
    private int searchIdx;
    private String keyword;
}
