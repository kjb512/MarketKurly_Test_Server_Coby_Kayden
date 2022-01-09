package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "/^[a-z]+[a-z0-9]{5,15}$/g", message = "6자 이상 영문 혹은 영문과 숫자를 조합")
    private String id;
    @NotNull
    @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{9,15}$/", message = "비밀번호는 영문/숫자 조합 10~16자")
    private String pwd;
    @NotNull
    @Email
    private String email;
}
