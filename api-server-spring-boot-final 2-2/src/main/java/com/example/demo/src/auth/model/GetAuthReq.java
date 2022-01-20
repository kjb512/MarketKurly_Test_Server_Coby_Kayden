package com.example.demo.src.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class GetAuthReq {
    @NotEmpty
    @Pattern(regexp = "^\\d{3}+\\d{4}+\\d{4}$", message = "휴대폰 번호 형식이 아님 01xyyyyzzzz(하이픈 없이)")
    private String phone;

    public GetAuthReq() {

    }
}
