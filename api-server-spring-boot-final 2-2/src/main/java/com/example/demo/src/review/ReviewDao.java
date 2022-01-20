package com.example.demo.src.review;


import com.example.demo.src.review.model.ReviewDto;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.src.users.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createReview(ReviewDto reviewDto, String imageUrl) {
        String query = "insert into Review(title, content, productIdx, userIdx,imageUrl) values (?,?,?,?,?);";
        this.jdbcTemplate.update(query, reviewDto.getTitle(), reviewDto.getContent(), reviewDto.getProductIdx(), reviewDto.getUserIdx(), imageUrl);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<ReviewRes> getReviewList(int productIdx) {
        String query = "select *\n" +
                "from Review left join User U on Review.userIdx = U.userIdx\n" +
                "where productIdx=? and Review.status='ACTIVE';";
        return this.jdbcTemplate.query(query, (rs, rowNum) -> new ReviewRes(rs.getInt("reviewIdx"),
                rs.getInt("productIdx"),
                rs.getInt("userIdx"),
                rs.getString("name"),
                rs.getString("title"),
                rs.getTimestamp("createAt"),
                ""), productIdx
        );
    }

    public ReviewInfoRes getReviewInfo(int reviewIdx) {
        String query = "select R.reviewIdx,R.productIdx,R.userIdx,U.name,R.title,R.content,R.imageUrl,R.createAt\n" +
                "from Review as R left join User U on R.userIdx = U.userIdx\n" +
                "where reviewIdx=? and R.status='ACTIVE';";
        return this.jdbcTemplate.queryForObject(query, (rs, rowNum) -> new ReviewInfoRes(rs.getInt("reviewIdx"),
                rs.getInt("productIdx"),
                rs.getInt("userIdx"),
                rs.getString("name"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("imageUrl"),
                rs.getTimestamp("createAt"),
                ""), reviewIdx);
    }

    public void updateReview(String title, String content, int reviewIdx) {
        String query = "update Review set title=?,content=? where reviewIdx=?;";
        this.jdbcTemplate.update(query, title, content, reviewIdx);
    }

    public void deleteReviewList(int reviewIdx) {
        String query = "update Review set status='INACTIVE' where reviewIdx=?;";
        this.jdbcTemplate.update(query, reviewIdx);
    }
}
