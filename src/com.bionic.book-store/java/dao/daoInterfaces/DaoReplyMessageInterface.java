package dao.daoInterfaces;

import entities.ReplyMessage;

import java.util.List;

/**
 * Created by jsarafajr on 26.07.14.
 */
public interface DaoReplyMessageInterface {
    List<ReplyMessage> selectAll();
    List<ReplyMessage> getModeratorMessages();
    List<ReplyMessage> getAdminMessages();
    ReplyMessage selectById(int id);
    void insert(ReplyMessage message);
    void update(ReplyMessage message);
    void delete(ReplyMessage message);
    void delete(int id);
}
