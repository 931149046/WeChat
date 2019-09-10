package we_chat.service;


import org.springframework.web.bind.annotation.RequestMapping;
import we_chat.pojo.The;

import javax.websocket.server.PathParam;
import java.util.List;

public interface TheService {
    public List<The> getAll(); //查询通讯录表所有数据

    public List<The> getAllById(String uid); //根据用户id进行查询对应当前用户的好友

    public void insert(String name,String image,String uid,String udid); //添加好友

}
