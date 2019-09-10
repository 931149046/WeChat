package we_chat.service;


import we_chat.pojo.Chat;

import java.util.List;

public interface ChatService {
    public void install(String name, String porimage,String did,String uid); //添加临时聊天人的信息

    public List<Chat> findAll(); //查询所有聊天人的信息表

    public List<Chat> getById(String id); //根据id查询聊天人表

    public List<Chat> getAllById(String uid); //根据用户id进行查询对应当前用户聊天的用户

    public void deleteById(String id);

    List<Chat> getBydidpkId(String id);
}
