package com.noob.service.impl;

import com.noob.dao.ReplyDAO;
import com.noob.domain.Reply;
import com.noob.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    ReplyDAO replyDAO;

    public void publishReply(Reply reply) {
        replyDAO.addReplay(reply);
    }

    public List<Reply> getReplyByMessageID(int messageID) {
        return replyDAO.findReplayByMsgID(messageID);
    }

    public int getCountByMessageID(int messageID) {
        return replyDAO.findCountByMsgID(messageID);
    }

    @Autowired
    public void setReplyDAO(ReplyDAO replyDAO) {
        this.replyDAO = replyDAO;
    }
}
