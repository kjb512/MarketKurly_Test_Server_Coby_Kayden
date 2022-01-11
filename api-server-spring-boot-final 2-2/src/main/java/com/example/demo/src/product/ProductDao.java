package com.example.demo.src.product;

import com.example.demo.src.product.model.ProductInfoRes;
import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getProductByIdx(int productIdx){
        String getProductQuery = "select * from Product where productIdx=?";
        return this.jdbcTemplate.query(getProductQuery,
                (rs,rowNum) -> new ProductInfoRes(
                        rs.getInt("productIdx"),
                        rs.getString()),productIdx
        );
    }
}
