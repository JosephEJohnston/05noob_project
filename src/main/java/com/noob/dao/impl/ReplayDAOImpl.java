package com.noob.dao.impl;

import com.noob.dao.ReplyDAO;
import com.noob.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplayDAOImpl implements ReplyDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addReplay(Reply reply) {
        String sql = " INSERT INTO tb_reply " +
                " (replyContext, employeeID, replyTime, messageID) " +
                " VALUES (?, ?, ?, ?) ";

        jdbcTemplate.update(sql, reply.getReplyContext(), reply.getEmployeeID(),
                reply.getReplyTime(), reply.getMessageID());
    }


    public List<Reply> findReplayByMsgID(int messageID) {
        String sql = " SELECT * FROM tb_reply WHERE messageID=? ";
        List<Reply> list;

        list = jdbcTemplate.query(sql, new Object[]{messageID} , new BeanPropertyRowMapper<Reply>(Reply.class));

        return list;
    }

    public int findCountByMsgID(int messageID) {
        String sql = " SELECT count(*) FROM tb_reply WHERE messageID=? ";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{messageID}, Integer.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("ERROR: findCountByMsgID");
        }

        throw new NullPointerException();
    }


}
