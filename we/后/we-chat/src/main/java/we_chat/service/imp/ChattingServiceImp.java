package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.ChattingDao;
import we_chat.dao.UserDao;
import we_chat.pojo.ChatContentObj;
import we_chat.pojo.Chatting;
import we_chat.pojo.ChattingNew;
import we_chat.pojo.User;
import we_chat.service.ChattingService;
import we_chat.tool.Uuid;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ChattingServiceImp implements ChattingService {
    @Autowired
    public ChattingDao chattingDao;

    @Autowired
    public UserDao userDao;

    public String strid = "";

    /*@Override
    public void insertAll(String fid, String id, String content, String image) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String newdate = simpleDateFormat.format(date);
        chattingDao.insterAll(Uuid.getUUID(), fid, id, content, newdate, image);
    }*/

    @Override
    public List<ChattingNew> getByIdAndContent(String uid, String content) {
        List<ChattingNew> chattingNewList = new ArrayList<>();
        User user = userDao.getByUid(uid);  //再根据传进来的uid查询用户表获取当前发送方的头像和昵称
        List<Chatting> chatting = chattingDao.getByIdAndContent(content, uid);  //根据u_id查询聊天记录
        for (Chatting c : chatting) { //循环聊天记录
            ChattingNew chattingNew = new ChattingNew(); //创建新对象,返回重新封装的
            chattingNew.setId(c.getChId());  //设值聊天记录id
            chattingNew.setUname(user.getuName());  //设值发送方的昵称
            chattingNew.setUimage(user.getuImage()); //设值发送方的头像
            User user1 = userDao.getByUid(c.getChFid());  //获取发送方id查询,发送方昵称和头像
            chattingNew.setFId(user1.getuName());  //设值发送发方的名字
            chattingNew.setFimage(user1.getuImage()); //设值发送方的头像
            chattingNew.setContent(c.getChContent()); //聊天内容
            chattingNew.setTime(c.getChTime()); //设值聊天时间
            chattingNewList.add(chattingNew);
        }
        return chattingNewList;
    }

    @Override
    public void deleteUid(String uid) { //根据发送方id删除对应的聊天记录
        chattingDao.deleteUid(uid);
    }

    @Override
    public List<Chatting> getChUidOrChFid(String id,String ids) {
        return chattingDao.findByChUidOrChFid(id,ids);
    }
}
