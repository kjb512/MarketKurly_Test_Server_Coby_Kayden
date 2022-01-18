package com.example.demo.src.Coupon;

import com.example.demo.src.Coupon.model.GetCouponsRes;
import com.example.demo.src.Coupon.model.PostCouponReq;
import com.example.demo.src.orders.model.GetOrdersRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CouponDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createCouponUser(PostCouponReq postCouponReq) {
        String createCouponUserQuery = "insert into CouponUser( userIdx, couponIdx) values (?,?)";
        Object[] createCouponUserParams = new Object[]{postCouponReq.getUserIdx(), postCouponReq.getCouponIdx()};
        this.jdbcTemplate.update(createCouponUserQuery, createCouponUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetCouponsRes> getCouponsByUser(int userIdx) {
        String getCouponsByUserQuery =  "select C.discount, C.name, C.deadLine, CU.status from User U " +
                "inner join CouponUser CU on U.userIdx = CU.userIdx " +
                "inner join Coupon C on CU.couponIdx = C.couponIdx " +
                "where U.userIdx = ? and not CU.status = 'USED'";
        int getCouponsByUserParams = userIdx;
        return this.jdbcTemplate.query(getCouponsByUserQuery,
                (rs, rowNum) -> new GetCouponsRes(
                        rs.getInt("discount"),
                        rs.getString("name"),
                        rs.getString("deadLine"),
                        rs.getString("status")),
                getCouponsByUserParams);
    }

    public List<GetCouponsRes> getAvailabeCoupons(int userIdx, int cartIdx) {
        String getAvailabeCouponsQuery =  "select CO.name, CO.discount, CO.deadLine,CO.status from Cart C " +
                "inner join CartProduct CP on C.cartIdx = CP.cartIdx " +
                "inner join Product P on CP.productIdx = P.productIdx " +
                "inner join CouponProduct CP2 on P.productIdx = CP2.productIdx  " +
                "inner join (select CU.couponUserIdx , C.name, C.discount, C.deadLine, CU.status, CU.couponIdx from User U " +
                    "inner join CouponUser CU on U.userIdx = CU.userIdx " +
                    "inner join Coupon C on CU.couponIdx = C.couponIdx " +
                    "where U.userIdx = ?) as CO on CO.couponIdx = CP2.couponIdx " +
                "where C.cartIdx = ? and not CO.status = 'USED'";
        Object[] getAvailabeCouponsParams = new Object[]{userIdx, cartIdx};
        return this.jdbcTemplate.query(getAvailabeCouponsQuery,
                (rs, rowNum) -> new GetCouponsRes(
                        rs.getInt("discount"),
                        rs.getString("name"),
                        rs.getString("deadLine"),
                        rs.getString("status")),
                getAvailabeCouponsParams);
    }

    public int cancelCouponUser(int userIdx, int couponUserIdx) {
        String cancelLikeQuery = "update CouponUser set status = 'USED' where userIdx= ? and couponUserIdx = ?";
        Object[] cancelLikeParams = new Object[]{userIdx, couponUserIdx};

        return this.jdbcTemplate.update(cancelLikeQuery,cancelLikeParams);
    }
}
