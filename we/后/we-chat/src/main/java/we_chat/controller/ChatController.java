package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.Chat;
import we_chat.pojo.The;
import we_chat.service.ChatService;

import java.util.List;
import java.util.Optional;

//聊天临时表(detail)
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    public ChatService chatService;
    @RequestMapping("/install")
    public void install(@Param("name") String name, @Param("porimage") String porimage,@Param("did") String did,@Param("uid") String uid){ //向聊天表中添加聊天人信息
        chatService.install(name,porimage,did,uid);
    }

    @RequestMapping("/getAll")
    public List<Chat> getAll(){ //查询所有聊天人的信息表
       return chatService.findAll();
    }

    @RequestMapping("/getById/{id}")
    public List<Chat> getById(@PathVariable("id") String id){ //根据id查询聊天人表
       return chatService.getById(id);
    }

    @RequestMapping("/getAllById/{uid}")
    public List<Chat> getAllById(@PathVariable("uid") String uid){ //根据用户id进行查询对应当前用户聊天的用户
        return chatService.getAllById(uid);
    }

    @RequestMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){ //根据用户id进行查询对应当前用户聊天的用户
         chatService.deleteById(id);
    }

    @RequestMapping("/getBydidpkId/{id}")
    public List<Chat> getBydidpkId(@PathVariable("id") String id){ //根据id查询聊天人表
        return chatService.getBydidpkId(id);
    }
}
