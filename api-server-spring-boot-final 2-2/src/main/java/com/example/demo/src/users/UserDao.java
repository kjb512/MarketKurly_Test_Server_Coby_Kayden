package com.example.demo.src.users;


import com.example.demo.src.users.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("phone"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from User where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("phone")),
                getUsersByEmailParams);
    }


    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from User where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("phone")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User(id, pwd, name, email, phone, birthDate, gender, isSmsReceiveAgree, isEmailReceiveAgree, isTermsOfUseAgree, isPersonalInfoCollectAgree, isPersonalInfoUsageAgree, isAgeAboveForteen, recommender, eventName) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getId(),postUserReq.getPwd(),postUserReq.getName(),postUserReq.getEmail(),postUserReq.getPhone(), postUserReq.getBirthDate(), postUserReq.getGender(), postUserReq.getIsSmsReceiveAgree(),postUserReq.getIsEmailReceiveAgree(), postUserReq.getIsTermsOfUseAgree(), postUserReq.getIsPersonalInfoCollectAgree(), postUserReq.getIsPersonalInfoUsageAgree(), postUserReq.getIsAgeAboveForteen(), postUserReq.getRecommender(), postUserReq.getEventName()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public void createUserBySns(PostSnsUserReq postSnsUserReq) {
        String createUserQuery = "insert into User(type, id, pwd, name, phone,email, isTermsOfUseAgree, isPersonalInfoCollectAgree, isAgeAboveForteen) " +
                "values(?, ?,?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postSnsUserReq.getNameAttributeKey(), postSnsUserReq.getNameAttributeKey(), postSnsUserReq.getNameAttributeKey(), postSnsUserReq.getName(), "01011111111" ,postSnsUserReq.getEmail(), "Y", "Y","Y"};
        this.jdbcTemplate.update(createUserQuery, createUserParams);
    }


    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public int doubleCheckId(String id) {
        String doubleCheckIdQuery = "select exists(select id from User where id = ? and type = 'GENERAL')";
        String doubleCheckIdParams = id;
        return this.jdbcTemplate.queryForObject(doubleCheckIdQuery,
                int.class,
                doubleCheckIdParams);
    }

    public int modifyUserName(int userIdx, PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set name = ?,pwd = ?, email =?, phone =?, birthDate=?, gender=?, isPersonalInfoUsageAgree=?,isSmsReceiveAgree=?, isEmailReceiveAgree=? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getName(), patchUserReq.getPwd(), patchUserReq.getEmail(), patchUserReq.getPhone(), patchUserReq.getBirthDate(), patchUserReq.getGender(),patchUserReq.getIsPersonalInfoUsageAgree(),patchUserReq.getIsSmsReceiveAgree(),patchUserReq.getIsEmailReceiveAgree(), userIdx};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userIdx, id,pwd,name,email from User where id = ?";
        String getPwdParams = postLoginReq.getId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("id"),
                        rs.getString("pwd"),
                        rs.getString("name"),
                        rs.getString("email")
                ),
                getPwdParams
                );

    }

    public int getDeliveryInfoByUser(int userIdx) {
        String getDeliveryIndoByUserQuery = "select DI.deliveryInfoIdx from User " +
                "inner join DeliveryInfo DI on User.userIdx = DI.userIdx " +
                "where DI.userIdx = ? and DI.isDefaultAddress = 'T'";
        int getDeliveryIndoByUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getDeliveryIndoByUserQuery,
                int.class,
                getDeliveryIndoByUserParams);
    }

    public int getCartIdx(int userIdx) {
        String getCartIdxQuery = "select cartIdx from Cart where userIdx = ? and status='ACTIVE'";
        int getCartIdxParams = userIdx;
        return this.jdbcTemplate.queryForObject(getCartIdxQuery,
                int.class,
                getCartIdxParams);
    }


}
