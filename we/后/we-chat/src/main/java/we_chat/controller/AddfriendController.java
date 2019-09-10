package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.AddState;
import we_chat.pojo.Addfriend;
import we_chat.pojo.NewAddfriend;
import we_chat.service.AddfriendService;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RequestMapping("/addfriend")
@RestController
public class AddfriendController {
    @Autowired
    public AddfriendService addfriendService;

    @RequestMapping("/insert")
    public void insert(@PathParam("uid") String uid, @PathParam("uname") String uname, @PathParam("value") String value, @PathParam("udid") String udid) { //添加好友
        addfriendService.insert(uid, uname, value, udid);
    }

    @RequestMapping("/getfidAndState/{jid}")
    public List<Addfriend> getjidAndState(@PathVariable("jid") String jid) { //查询等待用户验证表中有没有需要进行等待处理的表
        return addfriendService.getjidAndState(jid);
    }

    @RequestMapping("/updateFindstate/{jid}")
    public void updateFindstate(@PathVariable("jid") String jid) { //更新是否查看状态
        addfriendService.updateFindstate(jid);
    }

    @RequestMapping("/getjidAndStateAndImage/{jid}")
    public List<NewAddfriend> getjidAndStateAndImage(@PathVariable("jid") String jid) { //查询等待用户验证表中有没有需要进行等待处理的表
        return addfriendService.getjidAndStateAndImage(jid);
    }

    @RequestMapping("/updateStateWhereJId/{state}/{jid}/{fid}")
    public void updateStateWhereJId(@PathVariable("state") String state, @PathVariable("jid") String jid, @PathVariable("fid") String fid) { //查询等待用户验证表中有没有需要进行等待处理的表
        addfriendService.updateStateWhereJId(state, jid, fid);
    }

    @RequestMapping("/findByFId/{fid}")
    public List<AddState> findByFId(@PathVariable("fid") String fid) { //新的朋友查看的信息
        return addfriendService.findByFId(fid);
    }


}
