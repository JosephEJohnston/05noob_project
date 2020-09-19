package com.noob.dao;

import com.noob.domain.Message;
import com.noob.domain.Page;

import java.util.List;

public interface MessageDAO {

    //定义添加消息的方法
    void addMessage(Message message);

    //定义修改消息的方法
    void updateMessage(Message message);

    //定义删除消息的方法
    void deleteMessage(int messageID);

    //定义按分页查询消息的方法
    List<Message> findPageMessage(Page page);

    //定义按 ID 查询消息的方法
    Message findMessageById(int messageID);

    //定义查询消息记录数
    int findAllCount();
}
