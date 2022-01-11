package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserReq {
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z1-9]{9,15}", message = "비밀번호는 영문/숫자 조합 10~16자")
    private String pwd;
    @NotEmpty
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^\\d{3}+\\d{4}+\\d{4}$", message = "휴대폰 번호 형식이 아님 01xyyyyzzzz(하이픈 없이)")
    private String phone;
    @Pattern(regexp = "^(19|20)\\d{2}-\\d{2}-\\d{2}$", message = "형식 (19xx-xx-xx or 20xx-xx-xx)가 아닙니다 ")
    private String birthDate;
    @Pattern(regexp = "[MF]", message = "M/F 중 하나를 입력하여야 합니다.")
    private String gender;
    @Pattern(regexp = "[YN]", message = "Y/N 중 하나를 입력하여야 합니다.")
    private String PIUFlag;
    @Pattern(regexp = "[YN]", message = "Y/N 중 하나를 입력하여야 합니다.")
    private String smsFlag;
    @Pattern(regexp = "[YN]", message = "Y/N 중 하나를 입력하여야 합니다.")
    private String emailFlag;
}
