package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class PostLoginReq {
    @NotEmpty
    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,15}", message = "6자 이상 영문 혹은 영문과 숫자를 조합")
    private String id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z1-9]{9,15}", message = "비밀번호는 영문/숫자 조합 10~16자")
    private String pwd;
}
