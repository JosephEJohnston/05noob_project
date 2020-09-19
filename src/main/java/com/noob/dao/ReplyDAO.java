package com.noob.dao;

import com.noob.domain.Reply;

import java.util.List;

public interface ReplyDAO {
    //添加回复
    public void addReplay(Reply reply);

    public List<Reply> findReplayByMsgID(int messageID);

    //查询消息回复记录数
    public int findCountByMsgID(int messageID);
}
