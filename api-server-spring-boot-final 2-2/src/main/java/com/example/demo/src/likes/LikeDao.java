package com.example.demo.src.likes;

import com.example.demo.src.likes.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LikeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createLike(PostLikeReq postLikeReq) {
        String createLikeQuery = "insert into Likes(userIdx, productIdx) values (?,?)";
        Object[] createLikeParams = new Object[]{postLikeReq.getUserIdx(), postLikeReq.getProductIdx()};
        this.jdbcTemplate.update(createLikeQuery, createLikeParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetLikeRes> getLike(int userIdx) {
        String getLikeQuery = "select L.productIdx, P.profileImageUrl, P.title,P.price,P.discount from Likes L " +
                "left join Product P on P.productIdx = L.productIdx " +
                "where userIdx = ? and L.status = 'ACTIVE'";
        int getLikeParams = userIdx;
        return this.jdbcTemplate.query(getLikeQuery,
                (rs, rowNum) -> new GetLikeRes(
                        rs.getInt("productIdx"),
                        rs.getString("profileImageUrl"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getInt("discount")),
                getLikeParams);
    }

    public GetLikeCountRes getLikeCount(int userIdx) {
        String getLikeCountQuery = "select count(likeIdx) as counts from Likes " +
                "where userIdx = ? and status = 'ACTIVE'";
        int getLikeCountParams = userIdx;
        return this.jdbcTemplate.queryForObject(getLikeCountQuery,
                (rs, rowNum) -> new GetLikeCountRes(
                        rs.getInt("counts")),
                getLikeCountParams);
    }

    public int getLikeProduct(int userIdx, int productIdx) {
        String GetLikeProductQuery = "select count(likeIdx) from Likes " +
                "where userIdx = ? and productIdx = ?";
        Object[] GetLikeProductParams = new Object[]{userIdx, productIdx};
        return this.jdbcTemplate.queryForObject(GetLikeProductQuery, int.class,GetLikeProductParams);
    }

    public int cancelLike(DelteteLikeReq delteteLikeReq) {
        String cancelLikeQuery = "update Likes set status = 'INACTIVE'" +
                "where userIdx= ? and productIdx = ? ";
        Object[] cancelLikeParams = new Object[]{delteteLikeReq.getUserIdx(), delteteLikeReq.getProductIdx()};

        return this.jdbcTemplate.update(cancelLikeQuery,cancelLikeParams);
    }
}
