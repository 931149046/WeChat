package we_chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.The;
import we_chat.service.imp.TheServiceImp;

import javax.websocket.server.PathParam;
import java.util.List;

//通讯录控制器
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/the")
public class TheController {
    @Autowired
    public TheServiceImp theServiceImp;

    @RequestMapping("/getAll")
    public List<The> getAll() { //查询通讯录表所有数据
        return theServiceImp.getAll();
    }

    @RequestMapping("/getAllById/{uid}")
    public List<The> getAllById(@PathVariable("uid") String uid) { //根据用户id进行查询对应当前用户的好友
        return theServiceImp.getAllById(uid);
    }

    @RequestMapping("/insert")
    public void insert(@PathParam("name") String name, @PathParam("image") String image, @PathParam("uid") String uid,@PathParam("udid") String udid) { //添加好友
        theServiceImp.insert(name, image, uid,udid);
    }
}
