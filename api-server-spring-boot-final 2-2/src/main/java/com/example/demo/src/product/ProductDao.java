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

    public ProductInfoRes getProductByIdx(int productIdx){
        String getProductQuery = "select * from Product where productIdx=?";
        return this.jdbcTemplate.query(getProductQuery,
                (rs,rowNum) -> new ProductInfoRes(
                        rs.getInt("productIdx"),
                        rs.getString("title"),
                        rs.getString("subTitle"),
                        rs.getInt("price"),
                        rs.getInt("discount"),
                        rs.getString("profileImageUrl"),
                        rs.getString("contentTitle"),
                        rs.getString("contentSubTitle"),
                        rs.getString("contentImageUrl"),
                        rs.getString("ingredientImageUrl"),
                        rs.getString("saleUnit"),
                        rs.getString("weight"),
                        rs.getString("deliveryType"),
                        // packagingType
                        rs.getString("expirationDate"),
                        rs.getString("checkPointImageUrl"),
                        rs.getString("detailInfoImageUrl"),
                        rs.getDate("saleDeadLine"),
                        rs.getString(),
                        rs.getString(),
                        rs.getInt(),
                        rs.getString(),
                        // List<AllergyDto>
                        //BrandDTO
                        //List<TipContentDto>
                        ),productIdx
        );
    }
}
