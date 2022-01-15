package com.example.demo.src.cart;

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

    public void createUserCart(int userIdx) {
        String query = "insert Cart set userIdx=?;";
        this.jdbcTemplate.update(query, userIdx);
    }

    // cart query 모듈화

    // get product
    // 여기서 product 정보만 가져가고 + count
    public List<ProductMiniInfoForCartRes> getUserCartByCold(int userIdx) {

        String query = "select *, count(CP.productIdx) as countProduct\n" +
                "from (Cart\n" +
                "    right join CartProduct CP on Cart.cartIdx = CP.cartIdx)\n" +
                "         left join Product on CP.productIdx = Product.productIdx\n" +
                "GROUP BY CP.productIdx, CP.status, Cart.userIdx, Product.packagingTypeIdx\n" +
                "HAVING CP.status = 'ACTIVE'\n" +
                "   and Cart.userIdx = ?\n" +
                "   and Product.packagingTypeIdx =\n" +
                "       (SELECT PackagingType.packagingTypeIdx from PackagingType where PackagingType.name = '냉장/스티로폼');";
        return this.jdbcTemplate.query(query, (rs, rowNum) -> new ProductMiniInfoForCartRes(), userIdx);
    }


    // add product
    public void addProductInCart(int userIdx, int productIdx) {
        String query = "insert into CartProduct(productIdx, cartIdx)\n" +
                "values (?, (select cartIdx\n" +
                "            from Cart\n" +
                "            where userIdx = ?));";
        this.jdbcTemplate.update(query, userIdx);
    }

    // delete 한개 product - status 변경(cartProductIdx 중에 제일 높은)
    public void deleteProductInCart(int userIdx) {
        String query = "update CartProduct\n" +
                "set status = 'INACTIVE'\n" +
                "where (select Cart.cartIdx\n" +
                "       from Cart\n" +
                "       where userIdx = ?) = cartIdx\n" +
                "  and productIdx = ?\n" +
                "  and status = 'ACTIVE'\n" +
                "order by cartProductIdx DESC limit 1;";
        this.jdbcTemplate.update(query, userIdx);
    }

    public void deleteProductInCartAll(int userIdx) {
        String query = "update CartProduct\n" +
                "set status = 'INACTIVE'\n" +
                "where (select Cart.cartIdx\n" +
                "       from Cart\n" +
                "       where userIdx = ?) = cartIdx\n" +
                "  and productIdx = ?\n" +
                "  and status = 'ACTIVE'\n" +
                "order by cartProductIdx DESC limit 1;";
        this.jdbcTemplate.update(query, userIdx);
    }


}
