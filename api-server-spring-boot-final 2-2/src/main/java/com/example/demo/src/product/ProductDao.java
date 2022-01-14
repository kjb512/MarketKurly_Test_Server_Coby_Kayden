package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
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
        String query = "select *\n" +
                "from (Product as p left join Brand as b on p.brandIdx = b.brandIdx)\n" +
                "         left join PackagingType as pt on p.packagingTypeIdx = pt.packagingTypeIdx\n" +
                "where productIdx = ?;";
        return this.jdbcTemplate.queryForObject(query,
                (rs,rowNum) -> new ProductInfoRes(
                        rs.getInt("productIdx"),
                        rs.getString("title"),
                        rs.getString("subTitle"),
                        rs.getInt("price"),
                        rs.getInt("discount"),
                        rs.getString("p.profileImageUrl"),
                        rs.getString("contentTitle"),
                        rs.getString("contentSubTitle"),
                        rs.getString("contentImageUrl"),
                        rs.getString("ingredientImageUrl"),
                        rs.getString("saleUnit"),
                        rs.getString("weight"),
                        rs.getString("deliveryType"),
                        new PackagingTypeDto(rs.getInt("pt.packagingTypeIdx"),
                                rs.getString("name")),
                        // packagingType
                        rs.getString("expirationDate"),
                        rs.getString("checkPointImageUrl"),
                        rs.getString("detailInfoImageUrl"),
                        rs.getDate("saleDeadLine"),
                        rs.getString("isKurlyOnly"),
                        rs.getString("isLimitQuantity"),
                        rs.getInt("accumulate"),
                        rs.getString("eventIdx"),
                        getAllergyDtos(productIdx),
                        new BrandDto(rs.getInt("b.brandIdx"),
                                rs.getString("brandInfo"),
                                rs.getString("contentImageUrl1"),
                                rs.getString("contentImageUrl2")),
                        getTipContentDtos(productIdx),
                        getproductGuideDtos(productIdx)
                        ),productIdx
        );
    }

    public List<AllergyDto> getAllergyDtos(int productIdx){
        String query = "select *\n" +
                "from ProductAllergy as pa right join Allergy a on pa.allergyIdx = a.allergyIdx\n" +
                "where pa.productIdx=?";
        return this.jdbcTemplate.query(query,
                (rs,rowNum) -> new AllergyDto(rs.getInt("a.allergy"),
                        rs.getString("content")),productIdx
        );
    }

    public List<TipContentDto> getTipContentDtos(int productIdx){
        String query = "select *\n" +
                "from TipContent\n" +
                "where productIdx=?";
        return this.jdbcTemplate.query(query,
                (rs,rowNum) -> new TipContentDto(rs.getInt("tipContentIdx"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("imageUrl")),productIdx
        );
    }
    public List<ProductGuideDto> getproductGuideDtos(int productIdx){
        String query = "select *\n" +
                "from ProductGuide\n" +
                "where productIdx=?;";
        return this.jdbcTemplate.query(query,
                (rs,rowNum) -> new ProductGuideDto(rs.getInt("productGuideIdx"),
                        rs.getString("content")),productIdx
        );
    }


    public List<ProductMiniInfoRes> getProductsBySubCategory(int subCatehgoryIdx){
        String query = "select *\n" +
                "from Product\n" +
                "where subCategoryIdx=?;";
        return this.jdbcTemplate.query(query,
                (rs,rowNum) -> new ProductMiniInfoRes(rs.getInt("productIdx"),
                        rs.getString("title"),
                        rs.getString("profileImageUrl"),
                        rs.getString("isKurlyOnly"),
                        rs.getString("isLimitQuantity"),
                        rs.getInt("price"),
                        rs.getInt("discount"),
                        0
                ),subCatehgoryIdx
        );
    }

    public List<ProductMiniInfoRes> getProductsByCategory(int categoryIdx){
        String query = "select *\n" +
                "from Product left join SubCategory SC on Product.subCategoryIdx = SC.subCategoryIdx\n" +
                "where CategoryIdx=?;";
        return this.jdbcTemplate.query(query,
                (rs,rowNum) -> new ProductMiniInfoRes(rs.getInt("productIdx"),
                        rs.getString("title"),
                        rs.getString("profileImageUrl"),
                        rs.getString("isKurlyOnly"),
                        rs.getString("isLimitQuantity"),
                        rs.getInt("price"),
                        rs.getInt("discount"),
                        0
                        ),categoryIdx
        );
    }
    public int checkProductIdx(int proudctIdx){
        String query = "select exists(select * from Product where productIdx =?);";
        return this.jdbcTemplate.queryForObject(query,int.class,proudctIdx);
    }

    public int checkCategoryIdx(int proudctIdx){
        String query = "select exists(select * from Category where categoryIdx =?);";
        return this.jdbcTemplate.queryForObject(query,int.class,proudctIdx);
    }

    public int checkSubCategoryIdx(int proudctIdx){
        String query = "select exists(select * from SubCategory where subCategoryIdx =?);";
        return this.jdbcTemplate.queryForObject(query,int.class,proudctIdx);
    }


}
