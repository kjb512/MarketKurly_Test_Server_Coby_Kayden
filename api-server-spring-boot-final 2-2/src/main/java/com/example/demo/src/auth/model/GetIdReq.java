package com.example.demo.src.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class GetIdReq {
    @NotEmpty
    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,15}", message = "6자 이상 영문 혹은 영문과 숫자를 조합")
    private String id;

    public GetIdReq() {
    }
}
