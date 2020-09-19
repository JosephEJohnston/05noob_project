package com.noob.dao.impl;

import com.noob.dao.MessageDAO;
import com.noob.domain.Message;
import com.noob.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addMessage(Message message) {
        String sql = " INSERT INTO tb_message " +
                " (messageTitle, messageContent, employeeID, publishTime) " +
                " VALUES (?, ?, ?, ?) ";

        jdbcTemplate.update(sql, message.getMessageTitle(),
                message.getMessageContent(), message.getEmployeeID(), new Date());
    }

    public void updateMessage(Message message) {
        String sql = " UPDATE tb_message SET " +
                " messageTitle=?, messageContent=?, publishTime=? " +
                " WHERE messageID=? ";

        jdbcTemplate.update(sql, message.getMessageTitle(), message.getMessageContent(),
                new Date(), message.getMessageID());
    }

    public void deleteMessage(int messageID) {
        String sql = " DELETE FROM tb_message WHERE messageID=? ";

        jdbcTemplate.update(sql, messageID);
    }

    public List<Message> findPageMessage(Page page) {
        //DQL 中的分页查询
        String sql = " SELECT * FROM tb_message " +
                " ORDER BY publishTime desc limit ?,? ";
        final List<Message> list;

        list = jdbcTemplate.query(sql, new Object[]{page.getBeginIndex(), page.getEveryPage()},
                new BeanPropertyRowMapper<Message>(Message.class));

        return list;
    }

    public Message findMessageById(int messageID) {
        String sql = " SELECT * FROM tb_message WHERE messageID=? ";
        Message message = null;

        try {
            message = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<Message>(Message.class), messageID);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int findAllCount() {
        String sql = " SELECT COUNT(*) FROM tb_message ";
        int count = 0;

        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("ERROR: findAllCount");
        }

        return count;
    }


}
