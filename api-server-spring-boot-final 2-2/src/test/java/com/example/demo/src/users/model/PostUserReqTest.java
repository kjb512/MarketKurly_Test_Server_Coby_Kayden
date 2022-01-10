package com.example.demo.src.users.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostUserReqTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "홍길동"; //null 테스트
        String id = "aaaaa1";
        String pwd = "passwordaaaaa123";
        String email = "aaaaa1111@naver.com";


        //when
        //PostUserReq req = new PostUserReq(name, id, pwd, email);

        //then
//        assertThat(req.getName()).isEqualTo(name);
//        assertThat(req.getId()).isEqualTo(id);
//        assertThat(req.getPwd()).isEqualTo(pwd);
//        assertThat(req.getEmail()).isEqualTo(email);
    }
}
