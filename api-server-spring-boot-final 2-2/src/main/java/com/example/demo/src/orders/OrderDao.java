package com.example.demo.src.orders;

import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.orders.model.PostOrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createOrder(PostOrderReq postOrderReq) {
        String createOrderQuery = "insert into `Order`(userIdx, cartIdx, paymentType, productPrice, amountOfPayment, discountPrice, deliveryPrice, couponDiscount, rewardDiscount, deliveryInfoIdx) values(?,?,?,?,?,?,?,?,?,?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getUserIdx(), postOrderReq.getCartIdx(), postOrderReq.getPaymentType(), postOrderReq.getProductPrice(), postOrderReq.getAmountOfPayment(), postOrderReq.getDiscountPrice(), postOrderReq.getDeliveryPrice(),postOrderReq.getCouponDiscount(),postOrderReq.getRewardDiscount(), postOrderReq.getDelieveryInfoIdx()};
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
}
