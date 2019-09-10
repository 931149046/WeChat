package we_chat.service;

import we_chat.pojo.AddState;
import we_chat.pojo.Addfriend;
import we_chat.pojo.NewAddfriend;

import java.util.List;

public interface AddfriendService {
    void insert(String fid, String fname, String content, String jid); //添加等待对方用户接受的信息

    List<Addfriend> getjidAndState(String jid);  //查询等待用户验证表中有没有需要进行等待处理的表

    List<NewAddfriend> getjidAndStateAndImage(String jid);

    void updateFindstate(String jid); //更新是否查看状态

    void  updateStateWhereJId(String state,String jid,String fid); //根据接收人id进行更新是否同意的状态

    List<AddState> findByFId(String fid); //根据发送者id进行查询
}
