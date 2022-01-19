package com.example.demo.src.cart;

import com.example.demo.src.cart.model.CartUserInfo;
import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createUserCart(int userIdx, int deliveryInfoIdx) {
        String query = "insert into Cart( userIdx, deliveryInfoIdx) values (?,?);";
        this.jdbcTemplate.update(query, userIdx, deliveryInfoIdx);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public void deleteCart(int cartidx){
        String query = "update Cart set status='INACTIVE' where cartIdx=?;";
        this.jdbcTemplate.update(query,cartidx);
    }

    // cart query 모듈화

    // get product
    // 여기서 product 정보만 가져가고 + count
    public List<ProductMiniInfoForCartRes> getUserCartByPackagingType(int cartIdx, int packagingTypeIdx) {
        String query = "select *\n" +
                "from (Cart\n" +
                "    right join CartProduct CP on Cart.cartIdx = CP.cartIdx)\n" +
                "         left join Product on CP.productIdx = Product.productIdx\n" +
                "Where CP.status = 'ACTIVE'\n" +
                "  and Cart.status='ACTIVE'\n" +
                "  and count> 0\n" +
                "  and Cart.cartIdx = ?\n" +
                "  and Product.packagingTypeIdx =\n" +
                "      (SELECT PackagingType.packagingTypeIdx from PackagingType where PackagingType.packagingTypeIdx = ?);";
        return this.jdbcTemplate.query(query, (rs, rowNum) -> new ProductMiniInfoForCartRes(rs.getInt("CP.productIdx"),
                rs.getString("title"),
                rs.getString("profileImageUrl"),
                rs.getInt("price"),
                rs.getInt("discount"),
                0,
                rs.getInt("count")), cartIdx,packagingTypeIdx);
    }


    // add product
    // insert
    public void addProductInCart(int productIdx, int userIdx) {
        String query = "insert into CartProduct(productIdx, cartIdx)\n" +
                "values (?, ?);";
        this.jdbcTemplate.update(query, productIdx, userIdx);
    }

    // count productCount
    public void addProductCount(int productIdx, int userIdx) {
        String query = "update CartProduct\n" +
                "                set count = count + 1\n" +
                "                where productIdx = ? and\n" +
                "                (SELECT Cart.cartIdx from Cart where  Cart.cartIdx=? and Cart.status='ACTIVE')=cartIdx;";
        this.jdbcTemplate.update(query, productIdx, userIdx);
    }

    // delete 한개 product - status 변경(cartProductIdx 중에 제일 높은)
    public void deleteProductInCart(int productIdx, int cartIdx) {
        String query = "update CartProduct\n" +
                "set count = count-1\n" +
                "where (select Cart.cartIdx\n" +
                "       from Cart\n" +
                "       where Cart.cartIdx = ? and Cart.status = 'ACTIVE') = cartIdx\n" +
                "  and productIdx = ?\n" +
                "  and status = 'ACTIVE'\n" +
                "  and count>0;";
        this.jdbcTemplate.update(query, cartIdx,productIdx);
    }

    public void deleteProductInCartAll(int productIdx, int userIdx) {
        String query = "update CartProduct\n" +
                "set count = 0\n" +
                "where (select cartIdx\n" +
                "       from Cart\n" +
                "       where Cart.cartIdx = ? and Cart.status = 'ACTIVE') = cartIdx\n" +
                "  and productIdx = ?\n" +
                "  and status = 'ACTIVE'\n" +
                "  and count>0;";
        this.jdbcTemplate.update(query, userIdx, productIdx);
    }

    public int checkProductInCartForAdd(int productIdx, int userIdx) {
        String query = "select exists\n" +
                "           (select *\n" +
                "            from CartProduct\n" +
                "                     left join Cart on CartProduct.cartIdx = Cart.cartIdx\n" +
                "            where productIdx = ?\n" +
                "              and Cart.cartIdx = ?\n" +
                "    and Cart.status='ACTIVE'\n" +
                "    ) as exist;";
        return this.jdbcTemplate.queryForObject(query, int.class, productIdx, userIdx);
    }

    public CartUserInfo getCartUserInfo(int cartIdx){
        String query = "select *\n" +
                "from (Cart\n" +
                "         left join DeliveryInfo DI on Cart.deliveryInfoIdx = DI.deliveryInfoIdx)\n" +
                "        left join DeliveryType on DI.deliveryType = DeliveryType.deliverTypeIdx\n" +
                "where Cart.cartIdx = ?\n" +
                "  and Cart.status = 'ACTIVE';";
        return this.jdbcTemplate.queryForObject(query,(rs, rowNum) -> new CartUserInfo(rs.getInt("cartIdx"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("extraAddress")),cartIdx);
    }

    public int checkCartFromUser(int userIdx){
        String query = "select EXISTS(select * from Cart where userIdx=? and status='ACTIVE')";
        return this.jdbcTemplate.queryForObject(query,int.class,userIdx);
    }


}
