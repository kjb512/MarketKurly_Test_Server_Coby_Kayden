package com.example.demo.src.auth;

import com.example.demo.config.BaseException;
import com.example.demo.src.auth.model.GetAuthReq;
import com.example.demo.src.auth.model.GetAuthRes;
import com.example.demo.src.auth.model.GetIdReq;
import com.example.demo.src.auth.model.GetIdRes;
import com.example.demo.src.users.UserProvider;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserProvider userProvider;


    public GetAuthRes sendSms(GetAuthReq getAuthReq) throws BaseException{

        Random rand = new Random();
        String certNum = "";
        for(int i=0; i<4; i++){
            String ran = Integer.toString(rand.nextInt(10));
            certNum += ran;
        }

//        String api_key = "NCSIEG3XRVTOVU4C";
//        String api_sercret = "RSWDYESFSVW2URITBTMIXI9IDHWVQLJX";
//        Message sms  = new Message(api_key,api_sercret);
//
//        // 4 params(to, from, type, text) are mandatory. must be filled
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("to", getAuthReq.getPhone());    // 수신전화번호
//        params.put("from", "01065779308");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
//        params.put("type", "SMS");
//        params.put("text", "[마켓컬리] 인증알림\n아래 인증번호를 입력해주세요.\n\n인증번호 " + "["+certNum+"]");
//        params.put("app_version", "test app 1.2");
        return new GetAuthRes(certNum);
//        try {
//            JSONObject obj = (JSONObject) sms.send(params);
//            return new GetAuthRes(certNum);
//        } catch (CoolsmsException e) {
//            throw new BaseException(DATABASE_ERROR);
//        }

    }


}
