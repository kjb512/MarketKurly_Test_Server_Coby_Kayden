package com.example.demo.src.likes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PostLikeReq {
    @NotNull
    private int userIdx;
    @NotNull
    private int productIdx;
}
