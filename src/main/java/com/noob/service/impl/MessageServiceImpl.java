package com.noob.service.impl;

import com.noob.dao.MessageDAO;
import com.noob.domain.Message;
import com.noob.domain.Page;
import com.noob.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO;

    public void publishMsg(Message message) {
        messageDAO.addMessage(message);
    }


    public int findAllCount() {
        return messageDAO.findAllCount();
    }

    public List<Message> findMessagesByPage(Page page) {
        return messageDAO.findPageMessage(page);
    }

    public Message findMessageById(int messageID) {
        return messageDAO.findMessageById(messageID);
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
}
