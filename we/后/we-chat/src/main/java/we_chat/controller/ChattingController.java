package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import we_chat.dao.ChattingDao;
import we_chat.pojo.ChatContentObj;
import we_chat.pojo.Chatting;
import we_chat.pojo.ChattingNew;
import we_chat.service.ChattingService;

import javax.websocket.server.PathParam;
import java.util.List;

//聊天记录控制器
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/chatt")
public class ChattingController {
    @Autowired
    public ChattingService chattingService;

    @Autowired
    public ChattingDao chattingDao;

   /* @RequestMapping("/insertAll")
    public void insertAll(@PathParam("fid") String fid, @PathParam("id") String id, @PathParam("content") String content, @PathParam("image") String image) {
        chattingService.insertAll(fid, id, content, image);
    }*/

    @RequestMapping("/getAllById/{uid}/{content}")
    public List<ChattingNew> getAllById(@PathVariable("uid") String uid, @PathVariable("content") String content) {  //根据用户聊天内容表的uid和聊天内容查询用户返回结果
        return chattingService.getByIdAndContent(uid, content);
    }

    @RequestMapping("/deleteUid/{id}")
    public void deleteUid(@PathVariable("id") String uid) { //根据发送方用户的id删除(只删除发送方的聊天记录)
        chattingService.deleteUid(uid);
    }

    @RequestMapping("/getChUidOrChFid/{id}")
    public List<Chatting> getChUidOrChFid(@PathVariable("id") String uid) { //根据发送方用户的id删除(只删除发送方的聊天记录)
        String ids = uid;
        return chattingService.getChUidOrChFid(uid, ids);
    }

}
