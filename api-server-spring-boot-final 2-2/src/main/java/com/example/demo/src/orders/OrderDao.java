package com.example.demo.src.orders;

import com.example.demo.src.orders.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createOrder(PostOrderReq postOrderReq) {
        String createOrderQuery = "insert into `Order`(userIdx, cartIdx, couponUserIdx, paymentType, productPrice, amountOfPayment, discountPrice, deliveryPrice, couponDiscount, rewardDiscount) values (?,?,?,?,?,?,?,?,?,?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getUserIdx(), postOrderReq.getCartIdx(), postOrderReq.getCouponUserIdx(), postOrderReq.getPaymentType(), postOrderReq.getProductPrice(),
                postOrderReq.getAmountOfPayment(), postOrderReq.getDiscountPrice(), postOrderReq.getDeliveryPrice(),postOrderReq.getCouponDiscount(),postOrderReq.getRewardDiscount()};
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetOrdersRes> getOrdersByUser(int userIdx) {
        String getOrdersByUserQuery = "select O.orderIdx,P.title, CP.count as type,count(CP.cartIdx)-1 as cases, O.createAt, P2.name, O.amountOfPayment, O.deliveryStatus " +
                "from `Order` O " +
                "inner join CartProduct CP on O.cartIdx = CP.cartIdx " +
                "inner join Product P on P.productIdx = CP.productIdx " +
                "inner join Payment P2 on O.paymentType = P2.paymentIdx " +
                "where O.userIdx = ? " +
                "group by O.orderIdx";
        int getOrdersByUserParams = userIdx;
        return this.jdbcTemplate.query(getOrdersByUserQuery,
                (rs, rowNum) -> new GetOrdersRes(
                        rs.getInt("orderIdx"),
                        rs.getString("title"),
                        rs.getInt("type"),
                        rs.getInt("cases"),
                        rs.getString("createAt"),
                        rs.getString("name"),
                        rs.getInt("amountOfPayment"),
                        rs.getString("deliveryStatus")),
                getOrdersByUserParams);
    }

    public List<GetOrderProductRes> getOrderProduct(int orderIdx) {
        String getOrderProductQuery = "select CP.productIdx, P.title, P.price*CP.count as price, P.discount, CP.count, PT.name as packagingType " +
                "from `Order` O " +
                "inner join CartProduct CP on O.cartIdx = CP.cartIdx " +
                "inner join Product P on P.productIdx = CP.productIdx " +
                "inner join PackagingType PT on P.packagingTypeIdx = PT.packagingTypeIdx " +
                "where orderIdx = ?";
        int getOrderProductParams = orderIdx;
        return this.jdbcTemplate.query(getOrderProductQuery,
                (rs, rowNum) -> new GetOrderProductRes(
                        rs.getInt("productIdx"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getInt("discount"),
                        rs.getInt("count"),
                        rs.getString("packagingType")),
                getOrderProductParams);
    }

    public List<GetOrdersOftenRes> getOrdersOften(int userIdx) {
        String getOrdersOftenQuery = "select P.profileImageUrl,P.productIdx,P.title, P.price, count(P.productIdx) as count from `Order` O " +
                "inner join Cart C on O.cartIdx = C.cartIdx " +
                "inner join CartProduct CP on C.cartIdx = CP.cartIdx " +
                "inner join Product P on CP.productIdx = P.productIdx  " +
                "where O.userIdx = ? and O.status = 'ACTIVE'" +
                " group by P.productIdx " +
                "order by count DESC";
        int getOrdersOftenParams = userIdx;
        return this.jdbcTemplate.query(getOrdersOftenQuery,
                (rs, rowNum) -> new GetOrdersOftenRes(
                        rs.getString("profileImageUrl"),
                        rs.getInt("productIdx"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getInt("count")),
                getOrdersOftenParams);
    }

    public int getUserIdx(int orderIdx) {
        String getUserIdxQuery = "select userIdx from `Order` where orderIdx = ?";
        int getUserIdxParams = orderIdx;
        return this.jdbcTemplate.queryForObject(getUserIdxQuery, int.class, getUserIdxParams);
    }

    public String getOrderDeliveryStatus(int orderIdx) {
        String getOrderDeliveryStatusQuery = "select deliveryStatus from `Order` where orderIdx = ?";
        int getOrderDeliveryStatusParams = orderIdx;
        return this.jdbcTemplate.queryForObject(getOrderDeliveryStatusQuery, String.class, getOrderDeliveryStatusParams);
    }

    public GetOrderRes getOrder(int orderIdx) {
        String getOrderQuery = "select O.productPrice, O.deliveryPrice, O.discountPrice, O.couponDiscount, O.rewardDiscount, O.amountOfPayment, O.orderIdx, " +
                "U.name as userName, O.createAt as paymentDate, DI.receiver, DI.receiverPhone, DT.name as deliveryType, concat(DI.address,' ', DI.extraAddress ) as address, DI.afterMessageDeliveryTime " +
                "from `Order` O " +
                "inner join User U on U.userIdx = O.userIdx " +
                "inner join Cart C on O.cartIdx = C.cartIdx " +
                "inner join DeliveryInfo DI on C.deliveryInfoIdx = DI.deliveryInfoIdx " +
                "inner join DeliveryType DT on DI.deliveryType = DT.deliverTypeIdx " +
                "where O.orderIdx =?";
        int getOrderParams = orderIdx;
        return this.jdbcTemplate.queryForObject(getOrderQuery,
                (rs, rowNum) -> new GetOrderRes(
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
                getOrderParams);
    }

    public int cancelOrder(int orderIdx) {
        String cancelOrderQuery = "update `Order` set status = 'CANCELED', deliveryStatus='CANCELED' where orderIdx = ? ";
        int cancelOrderParams = orderIdx;

        return this.jdbcTemplate.update(cancelOrderQuery,cancelOrderParams);
    }


}
