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
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createReview(ReviewDto reviewDto, String imageUrl){
        String query = "insert into Review(title, content, productIdx, userIdx,imageUrl) values (?,?,?,?,?);";
        this.jdbcTemplate.update(query);
    }

//    public List<ReviewRes> getReviewList(){
//
//    }
//
//    public ReviewInfoRes getReviewInfo(){
//
//    }
//
//    public List<>

    public void deleteReviewList(){
        String query = "update Review set status='INACTIVE' where reviewIdx=?;";
        this.jdbcTemplate.update(query);
    }
}
