package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,15}", message = "6자 이상 영문 혹은 영문과 숫자를 조합")
    private String id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z1-9]{9,15}", message = "비밀번호는 영문/숫자 조합 10~16자")
    private String pwd;
    @NotEmpty
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^\\d{3}+\\d{4}+\\d{4}$", message = "휴대폰 번호 형식이 아님 01xyyyyzzzz(하이픈 없이)")
    private String phone;
    @NotEmpty
    private String adress;
    @NotEmpty
    private String extraAdress;
    @Pattern(regexp = "^(19|20)\\d{2}-\\d{2}-\\d{2}$", message = "형식 (19xx-xx-xx or 20xx-xx-xx)가 아닙니다 ")
    private String birthDate;
    @Pattern(regexp = "[MF]", message = "M/F 중 하나를 입력하여야 합니다.")
    private String gender;

    private String recommender;
    @Size(max = 45)
    private String eventName;
    @NotEmpty
    @Pattern(regexp = "[Y]", message = "필수 동의 이므로 Y를 입력하셔야 합니다.")
    private String isTermsOfUseAgree;
    @NotEmpty
    @Pattern(regexp = "[Y]", message = "필수 동의 이므로 Y를 입력하셔야 합니다.")
    private String isPersonalInfoCollectAgree;
    @Pattern(regexp = "[YN]", message = "Y/N 중 하나를 입력하여야 합니다.")
    private String isPersonalInfoUsageAgree;
    @Pattern(regexp = "[YN]", message = "Y/N 중 하나를 입력하여야 합니다.")
    private String isSmsReceiveAgree;
    @Pattern(regexp = "[YN]", message = "Y/N 중 하나를 입력하여야 합니다.")
    private String isEmailReceiveAgree;
    @NotEmpty
    @Pattern(regexp = "[Y]", message = "필수 동의 이므로 Y를 입력하셔야 합니다.")
    private String isAgeAboveForteen;

//    @Builder
//    public PostUserReq(String name, String id, String pwd, String email, String phone, String adress, Date birthDate, String gender, String recommender, String eventName, String TOUFlag, String PIFlag, String PIUFlag, String smsFlag, String emailFlag, String ageFlag) {
//        this.name = name;
//        this.id = id;
//        this.pwd = pwd;
//        this.email = email;
//        this.phone = phone;
//        this.adress = adress;
//        this.birthDate = birthDate;
//        this.gender = gender;
//        this.recommender = recommender;
//        this.eventName = eventName;
//        this.TOUFlag = TOUFlag;
//        this.PIFlag = PIFlag;
//        this.PIUFlag = PIUFlag;
//        this.smsFlag = smsFlag;
//        this.emailFlag = emailFlag;
//        this.ageFlag = ageFlag;
//    }
}
