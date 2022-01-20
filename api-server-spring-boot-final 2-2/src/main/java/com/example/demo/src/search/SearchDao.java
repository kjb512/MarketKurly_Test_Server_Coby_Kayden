package com.example.demo.src.search;


import com.example.demo.src.review.model.ReviewDto;
import com.example.demo.src.review.model.ReviewInfoRes;
import com.example.demo.src.review.model.ReviewRes;
import com.example.demo.src.search.model.SearchDeleteRes;
import com.example.demo.src.search.model.SearchRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SearchDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<SearchRes> getRecentSearch(int userIdx){
        String query = "select *\n" +
                "from SearchKeyword\n" +
                " group by keyword,userIdx,status\n" +
                "having userIdx=? and status='ACTIVE'\n" +
                "limit 10;";

        return this.jdbcTemplate.query(query,(rs, rowNum) -> new SearchRes(rs.getInt("searchKeywordIdx"),
                rs.getString("keyword")),userIdx);
    }

    public List<SearchRes> getSearchRecommend(String keyword){
        String query = "select *\n" +
                "from SearchKeyword\n" +
                "group by keyword\n" +
                "having keyword LIKE concat('%',?,'%');";

        return this.jdbcTemplate.query(query,(rs, rowNum) -> new SearchRes(rs.getInt("searchKeywordIdx"),
                rs.getString("keyword")),keyword);
    }

    public void addSearch(int userIdx, String keyword){
        String query = "insert into SearchKeyword(keyword,userIdx) values (?,?);";
        this.jdbcTemplate.update(query,keyword,userIdx);
    }

    public SearchDeleteRes deleteSearch(int searchKeywordIdx){
        String query = "update SearchKeyword set status='INACTIVE' where searchKeywordIdx=?;";
        this.jdbcTemplate.update(query,searchKeywordIdx);
        String resultQuery = "select *\n" +
                "from SearchKeyword\n" +
                "where searchKeywordIdx=?;";
        return this.jdbcTemplate.queryForObject(resultQuery,(rs, rowNum) -> new SearchDeleteRes(rs.getInt("searchKeywordIdx"),
                rs.getString("keyword"),
                rs.getString("status")),searchKeywordIdx);
    }


}
