package com.example.demo.src.users;



import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.auth.model.GetAuthReq;
import com.example.demo.src.auth.model.GetAuthRes;
import com.example.demo.src.auth.model.GetIdReq;
import com.example.demo.src.auth.model.GetIdRes;
import com.example.demo.src.users.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //중복
        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        // 아이디 중복확인 T: 중복안됨 F: 중복됨
        if(userProvider.doubleCheckId(postUserReq.getId()) ==1){
            throw new BaseException(DOUBLE_CHECK_ID);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPwd());
            postUserReq.setPwd(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int userIdx = userDao.createUser(postUserReq);
            //jwt 발급.
            String jwt = jwtService.createJwt(userIdx);
            return new PostUserRes(jwt,userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserName(int userIdx, PatchUserReq patchUserReq) throws BaseException {
        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(patchUserReq.getPwd());
            patchUserReq.setPwd(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int result = userDao.modifyUserName(userIdx, patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetIdRes doubleCheckId(String id) throws BaseException{
        // 아이디 중복확인 T: 중복됨 F: 중복안됨
        if(userProvider.doubleCheckId(id) ==1){
            throw new BaseException(DOUBLE_CHECK_ID);
        }
        return new GetIdRes("아이디 사용가능");
    }

}
