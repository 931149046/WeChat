package we_chat.service;

import we_chat.pojo.ChatContentObj;
import we_chat.pojo.Chatting;
import we_chat.pojo.ChattingNew;
import java.util.List;

public interface ChattingService {
    //public void insertAll(String fid, String id, String content,String image);
    public List<ChattingNew> getByIdAndContent(String uid, String content);


    void deleteUid(String uid); //根据发送方id删除对应的聊天记录

    public List<Chatting> getChUidOrChFid(String id,String ids);
}
