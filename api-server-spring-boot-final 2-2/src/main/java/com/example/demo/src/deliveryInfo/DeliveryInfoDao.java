package com.example.demo.src.deliveryInfo;

import com.example.demo.src.deliveryInfo.model.GetDeliveryInfoRes;
import com.example.demo.src.deliveryInfo.model.PatchDeliveryInfoReq;
import com.example.demo.src.deliveryInfo.model.PostDeliveyInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DeliveryInfoDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createDeliveryInfo(PostDeliveyInfoReq postDeliveyInfoReq) {
        String createUserQuery = "insert into DeliveryInfo(userIdx, address, extraAddress) values (?,?,?)";
        Object[] createUserParams = new Object[]{postDeliveyInfoReq.getUserIdx(), postDeliveyInfoReq.getAddress(), postDeliveyInfoReq.getExtraAddress()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetDeliveryInfoRes> getDeliveryInfo(int userIdx) {
        String getDeliveryInfoQuery = "select concat(address,' ', extraAddress) as address, receiver, receiverPhone, DT.name as deliveryType " +
                "from DeliveryInfo " +
                "left join DeliveryType DT on DT.deliverTypeIdx = DeliveryInfo.deliveryType " +
                "where userIdx = ?";
        int getDeliveryInfoParam = userIdx;
        return this.jdbcTemplate.query(getDeliveryInfoQuery,
                (rs, rowNum) -> new GetDeliveryInfoRes(
                        rs.getString("address"),
                        rs.getString("receiver"),
                        rs.getString("receiverPhone"),
                        rs.getString("deliveryType")),
                getDeliveryInfoParam);
    }

    public int modifyUserName(int userIdx, PatchDeliveryInfoReq patchDeliveryInfoReq) {
        String modifyUserNameQuery = "update DeliveryInfo set  " +
                "where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchDeliveryInfoReq.getExtraAddress(), patchDeliveryInfoReq.getReceiver(), patchDeliveryInfoReq.getReceiverPhone()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }
}
