package com.noob.service;

import com.noob.domain.Message;
import com.noob.domain.Page;

import java.util.List;

public interface MessageService {
    void publishMsg(Message message);

    int findAllCount();

    List<Message> findMessagesByPage(Page page);

    Message findMessageById(int messageID);
}
