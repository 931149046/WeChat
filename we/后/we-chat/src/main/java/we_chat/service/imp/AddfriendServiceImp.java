package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.AddfriendDao;
import we_chat.dao.InformDao;
import we_chat.dao.TheDao;
import we_chat.dao.UserDao;
import we_chat.pojo.*;
import we_chat.service.AddfriendService;
import we_chat.tool.Initial;
import we_chat.tool.Uuid;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AddfriendServiceImp implements AddfriendService {

    @Autowired
    public AddfriendDao addfriendDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public InformDao informDao;

    @Autowired
    public TheDao theDao;

    @Override
    public void insert(String fid, String fname, String content, String jid) { //添加等待对方用户接受的信息
        System.out.println("发送id" + fid);
        Addfriend addfriend = addfriendDao.findByFIdAndJId(fid, jid);
        if (content.equals("null")) {
            if (addfriend == null) {
                addfriendDao.insert(Uuid.getUUID(), fid, fname, "我是" + fname, jid, "等待同意", "未查看");
            }
        } else {
            if (addfriend == null) {
                addfriendDao.insert(Uuid.getUUID(), fid, fname, content, jid, "等待同意", "未查看");
            }
        }
    }

    @Override
    public List<Addfriend> getjidAndState(String jid) { //查询等待用户验证表中有没有需要进行等待处理的表
        return addfriendDao.getjidAndState(jid, "未查看");
    }

    @Override
    public List<NewAddfriend> getjidAndStateAndImage(String jid) {
        List<NewAddfriend> list = new ArrayList<>();
        //根据j_id查数据除了头像无法封装外,就先封装其他的数据,然后取出f_id查询user用户表取出头像封装新类返回
        List<Addfriend> addfriendList = addfriendDao.getjidAndStateAndImage(jid,"未查看");
        for (Addfriend add : addfriendList) {
            NewAddfriend newAddfriend = new NewAddfriend();
            newAddfriend.setId(add.getId());
            newAddfriend.setfId(add.getfId());
            newAddfriend.setfName(add.getfName());
            newAddfriend.setContent(add.getContent());
            newAddfriend.setjId(add.getjId());
            newAddfriend.setFindstate(add.getFindstate());
            newAddfriend.setState(add.getState());
            List<User> users = userDao.getById(add.getfId());
            for (User user : users) {
                newAddfriend.setFuserImage(user.getuImage());
            }
            list.add(newAddfriend);
        }
        return list;
    }

    @Override
    public void updateFindstate(String jid) { //更新是否查看状态
        addfriendDao.updateFindstate(jid, "已查看");
    }

    @Override
    public void updateStateWhereJId(String state, String jid, String fid) { //根据接收人id进行更新是否同意的状态
        System.out.println(state + "--" + jid + "--" + fid);
        addfriendDao.updateAddfriendFindState("已查看",fid,jid);
        List<User> users = userDao.getById(jid); //根据用户名id查询user表,取出用户名
        Inform inform = informDao.findByFIdAndJId(jid, fid);
        if (state.equals("同意")) { //判断传进来的是同意就更新状态为同意
            addfriendDao.updateStateWhereJId("同意", jid);
            //回复的消息内容(就是同意还是拒绝)
            for (User user : users) { //取出用户名
                if (inform == null) { //等于空才进行添加,保证了相同用户只能添加一次
                    informDao.insertInform(Uuid.getUUID(), jid, user.getuName(), user.getuName() + "同意了你的请求", fid, "同意", "未查看");
                    //往通讯录表中添加(添加在当前用户的通讯录中，当前是b用户,模拟的)，要保存a用户的信息
                    //向the通讯录表中保存的数据有id，t_name(通行录名字),t_porimage(通讯录头像),t_letter(通讯录的字母),u_id(关联当前用户的id)，ud_id(这是对应的用户号)
                    //根据传进来的用户id查询user表获取名字,头像,用户id
                    The the = theDao.findByUIdAndUdId(jid, fid);
                    if (the == null) {
                        User user1 = userDao.getByUid(fid);
                        String name = user1.getuName();
                        String letter = name.substring(0, 1); //截取第一个字符
                        String newLetter = Initial.getPinYinHeadChar(letter); //把截取到的第一个字符进行转换
                        theDao.insert(Uuid.getUUID(), user1.getuName(), user1.getuImage(), newLetter, jid, fid);
                    }
                    The the2 = theDao.findByUIdAndUdId(fid, jid);
                    if (the2 == null) {
                        //往通讯录表中添加(添加在对方用户的通讯录中,对方是A用户,模拟的)，要保存b用户的信息
                        User user2 = userDao.getByUid(jid);
                        String name2 = user2.getuName();
                        String letter2 = name2.substring(0, 1); //截取第一个字符
                        String newLetter2 = Initial.getPinYinHeadChar(letter2); //把截取到的第一个字符进行转换
                        theDao.insert(Uuid.getUUID(), user2.getuName(), user2.getuImage(), newLetter2, fid, jid);
                    }
                }
            }
        } else { //否则更新为拒绝
            addfriendDao.updateStateWhereJId("拒绝", jid);
            for (User user : users) {
                if (inform == null) {
                    informDao.insertInform(Uuid.getUUID(), jid, user.getuName(), user.getuName() + "拒绝了你的请求", fid, "拒绝", "未查看");
                }
            }
        }
    }

    @Override
    public List<AddState> findByFId(String fid) { //新的朋友查看的信息
        List<AddState> addStateList = new ArrayList<>();
        List<Addfriend> addfriendList = addfriendDao.findByFId(fid);
        for (Addfriend add : addfriendList) {
            AddState addState = new AddState();
            addState.setId(add.getId());
            addState.setValue(add.getContent());
            addState.setState(add.getState());
            User user = userDao.getByUid(add.getjId());
            addState.setImage(user.getuImage());
            addState.setName(user.getuName());
            addStateList.add(addState);
        }
        return addStateList;
    }
}
