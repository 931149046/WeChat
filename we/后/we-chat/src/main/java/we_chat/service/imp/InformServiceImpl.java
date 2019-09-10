package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.*;
import we_chat.pojo.Detail;
import we_chat.pojo.Inform;
import we_chat.pojo.NewInform;
import we_chat.pojo.User;
import we_chat.service.InformService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class InformServiceImpl implements InformService {
    @Autowired
    public InformDao informDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public TheDao theDao;

    @Autowired
    public DetailDao detailDao;

    @Autowired
    public CircleDao circleDao;

    @Autowired
    public ChattingDao chattingDao;

    @Autowired
    public ChatDao chatDao;

    @Autowired
    public AddfriendDao addfriendDao;

    @Override
    public List<Inform> findByFIdAndFindstate(String fid) { //根据f_id和findstate进行查询;条件是findstate要为未查询
        return informDao.findByJIdAndFindstate(fid, "未查看");
    }

    @Override
    public void updateJidState(String fid) { //根据fid进行更新查看状态
        informDao.updateJidState("已查看", fid);
    }

    @Override
    public List<NewInform> findByJId(String jid) { //根据jid进行查询
        List<NewInform> newInformList = new ArrayList<>();
        List<Inform> informs = informDao.findByJId(jid);
        for (Inform inform : informs) {
            NewInform newInform = new NewInform();
            newInform.setId(inform.getId());
            newInform.setfId(inform.getFId());
            newInform.setfName(inform.getFName());
            newInform.setContent(inform.getContent());
            newInform.setjId(inform.getJId());
            newInform.setState(inform.getState());
            newInform.setFindstate(inform.getFindstate());
            User user = userDao.getByUid(inform.getFId());
            newInform.setInformImage(user.getuImage());
            newInformList.add(newInform);
        }
        return newInformList;
    }

    @Override
    public void deleteRelationTable(String pid, String bid) {  //删除根据这两个id有关系的表数据整个数据库,pid是当前用户id。bid是被删除用户id
        theDao.deleteThe(pid,bid); //删除the表
        informDao.deleteInform(pid,bid); //删除infor表(添加好友的通知同意或者拒绝表),调用两次交换下位置。保证他们两个对方互接收的消息都被删除
        informDao.deleteInform(bid,pid);

       // detailDao.deleteDetail(bid); //删除detail表

        circleDao.deleteCircle(bid,pid); //删除circle表

        chattingDao.deleteChatting(pid,bid);  //删除chatting表(聊天表),调用两次交换下位置。保证他们两个对方互接收的消息都被删除
        chattingDao.deleteChatting(bid,pid);

        Detail detail = detailDao.findByTId(bid);  //先查询后取出d_id
        if (detail != null){
            chatDao.deleteChat(detail.getdId()); //根据didpk进行删除
        }


        addfriendDao.deleteAddfriend(pid,bid); //删除addfriend表(添加好友的通知等待同意或者拒绝表),调用两次交换下位置。保证他们两个对方互接收的消息都被删除
        addfriendDao.deleteAddfriend(bid,pid);
    }
}
