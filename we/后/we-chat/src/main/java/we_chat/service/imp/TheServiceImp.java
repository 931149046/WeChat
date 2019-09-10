package we_chat.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.TheDao;
import we_chat.pojo.The;
import we_chat.service.TheService;
import we_chat.tool.Initial;
import we_chat.tool.Uuid;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TheServiceImp implements TheService {
    @Autowired
    public TheDao theDao;

    @Override
    public List<The> getAll() { //查询通讯录表所有
        return theDao.getAll();
    }

    @Override
    public List<The> getAllById(String uid) { //根据用户id进行查询对应当前用户的好友
        return theDao.getAllById(uid);
    }

    @Override
    public void insert(String name, String image, String uid, String udid) {  //添加好友
        The the = theDao.getNumber(name);
        if (the == null) {
            String letter = name.substring(0, 1); //截取第一个字符
            String newLetter = Initial.getPinYinHeadChar(letter); //把截取到的第一个字符进行转换
            theDao.insert(Uuid.getUUID(), name, image, newLetter, uid, udid);
        }
    }
}
