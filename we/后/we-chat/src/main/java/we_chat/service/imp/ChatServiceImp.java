package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.ChatDao;
import we_chat.pojo.Chat;
import we_chat.pojo.The;
import we_chat.service.ChatService;
import we_chat.tool.Uuid;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class ChatServiceImp implements ChatService {
    @Autowired
    public ChatDao chatDao;

    @Override
    public void install(String name, String porimage,String did,String uid) { //添加临时聊天人的信息
        String id = Uuid.getUUID();
        Date date = new Date(); //获取当前的系统时间。
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm") ; //使用了默认的格式创建了一个日期格式化对象。
        String time = dateFormat.format(date); //可以把日期转换转指定格式的字符串
        System.out.println("当前的系统时间："+ time);
        List<Chat> chats = chatDao.getByIdAndUid(did,uid);
        if (chats.size() == 0){
            chatDao.install(id,name,porimage,time,did,uid);
        }
    }

    @Override
    public List<Chat> findAll() { //查询所有聊天人的信息表
        return chatDao.findAll();
    }

    @Override
    public List<Chat> getById(String id) { //根据id查询聊天人表
        return chatDao.getById(id);
    }
    @Override
    public List<Chat> getAllById(String uid) { //根据用户id进行查询对应当前用户聊天的用户
        return chatDao.getAllById(uid);
    }

    @Override
    public void deleteById(String id) {
        chatDao.deleteById(id);
    }

    @Override
    public List<Chat> getBydidpkId(String id) {
        return chatDao.getBydidpkId(id);
    }

}
