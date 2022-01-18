package com.example.demo.src.question;

import com.example.demo.src.question.model.AnswerRes;
import com.example.demo.src.question.model.QuestionRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class QuestionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createQuestion(int productIdx, int userIdx, String title, String question,String isLock){
        String query = "insert into Question(title,question,userIdx,isLock,productIdx) values(?,?,?,?,?);";

        this.jdbcTemplate.update(query,title,question,userIdx,isLock,productIdx);
    }

    public List<QuestionRes> getQuestionsWithAnswer(int productIdx){
        String query = "select *\n" +
                "from Question left join Answer A on Question.questionIdx = A.questionIdx and A.status='ACTIVE'\n" +
                "where productIdx=? and Question.status='ACTIVE'\n" +
                "order by Question.createAt DESC ;";

        return this.jdbcTemplate.query(query,(rs, rowNum) -> new QuestionRes(rs.getInt("Question.questionIdx"),
                rs.getInt("userIdx"),
                rs.getString("title"),
                rs.getString("question"),
                rs.getString("isAnswer"),
                rs.getString("isLock"),
                rs.getTimestamp("Question.createAt"),
                "",
                new AnswerRes(rs.getInt("answerIdx"),
                        rs.getString("answer"))),productIdx);
    }

    public void updateQuestion(int questionIdx, String title, String question, String isLock){
        String query = "update Question set title=?,question=?,isLock=? where questionIdx=?;";
        this.jdbcTemplate.update(query,title,question,isLock,questionIdx);
    }

    public void deleteQuestion(int questionIdx){
        String deleteAnswerQuery = "update Answer set status='INACTIVE' where questionIdx=?;";
        this.jdbcTemplate.update(deleteAnswerQuery,questionIdx);
        String deleteQuestionQuery ="update Question set status='INACTIVE' where questionIdx=?;";
        this.jdbcTemplate.update(deleteQuestionQuery,questionIdx);
    }

    public int creatAnswer(int answerIdx, String answer){
        String query ="insert into Answer(questionIdx, answer) values (?,?);";
        this.jdbcTemplate.update(query,answerIdx,answer);
        String queryForResult = "select questionIdx\n" +
                "from Answer\n" +
                "where answerIdx=?";
        return this.jdbcTemplate.queryForObject(queryForResult,int.class,answerIdx);
    }

}
