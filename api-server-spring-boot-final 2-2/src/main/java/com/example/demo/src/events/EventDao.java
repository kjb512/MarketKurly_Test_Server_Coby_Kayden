package com.example.demo.src.events;

import com.example.demo.src.events.model.GetEventRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Repository
public class EventDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public GetEventRes getEvent(int eventIdx) {
        String getEventQuery = "";
        int getEventParams = eventIdx;
        return this.jdbcTemplate.queryForObject(getEventQuery,
                (rs, rowNum) -> new GetEventRes(
                        rs.getInt("productPrice"),
                        rs.getInt("deliveryPrice"),
                        rs.getInt("discountPrice"),
                        rs.getInt("couponDiscount"),
                        rs.getInt("rewardDiscount"),
                        rs.getInt("amountOfPayment"),
                        rs.getInt("orderIdx"),
                        rs.getString("userName"),
                        rs.getString("paymentDate"),
                        rs.getString("receiver"),
                        rs.getString("receiverPhone"),
                        rs.getString("deliveryType"),
                        rs.getString("address"),
                        rs.getString("afterMessageDeliveryTime")),
                getEventParams);
    }
}
