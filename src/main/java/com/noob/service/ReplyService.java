package com.noob.service;

import com.noob.domain.Reply;

import java.util.List;

public interface ReplyService {

    //发布回复
    void publishReply(Reply reply);

    List<Reply> getReplyByMessageID(int messageID);

    int getCountByMessageID(int messageID);
}
