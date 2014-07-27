package dao.daoInterfaces;

import entities.ReplyMessage;

import java.util.List;

/**
 * Created by Eklerka on 7/24/2014.
 */
public interface DaoReplyMessageInterface {
    List<ReplyMessage> selectAll();
    ReplyMessage selectById(int id);
    void insert(ReplyMessage replyMessage);
    void update(ReplyMessage replyMessage);
    void delete(ReplyMessage replyMessage);
    void delete(int id);
    List<ReplyMessage> selectByReceiver(String receiver);
}
