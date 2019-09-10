package we_chat.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.*;
import we_chat.pojo.Detail;
import we_chat.service.DetailService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DetailServiceImp implements DetailService {
    @Autowired
    public DetailDao detailDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public AddfriendDao addfriendDao;

    @Autowired
    public ChatDao chatDao;

    @Autowired
    public ChattingDao chattingDao;

    @Autowired
    public CircleDao circleDao;

    @Autowired
    public InformDao informDao;

    @Autowired
    public TheDao theDao;


    @Override
    public List<Detail> getById(String id) { //根据t_id查询通讯录详细表对应的数据
        return detailDao.getById(id);
    }

    @Override
    public List<Detail> getByDid1(String id) {
        return detailDao.getByDid1(id);
    }

    @Override
    public List<Detail> getByDid(String id) {
        return detailDao.getByDid11(id);
    }

    @Override
    public Detail findByTId(String tid) {
        return detailDao.findByTId(tid);
    }

    @Override
    public void updateName(String name, String tid) {
        //更新addfriend添加好友表f_name字段(根据f_id更新)
        addfriendDao.updateFname(name, tid);
        //更新chat表首先用用户id根据t_id进行查询detail表取出d_id后。在chat表中根据取出来的值d_idpk进行更新用户名
        Detail detail = detailDao.findByTId(tid);
        chatDao.updateRname(name, detail.getdId());

        //更新chatting用户名(ch_name字段) 根据ch_uid进行更新
        chattingDao.updateCname(name, tid);
        //更新详细人表
        detailDao.updateName(name, tid);

        //更新inform根据f_id进行更新用户名
        informDao.updateFname(name, tid);
        //更新the表根据ud_id进行更新名字
        theDao.updateTname(name, tid);

        //更新user表根据u_id进行更新
        userDao.updateName(name, tid);
    }

    @Override
    public void updateSex(String sex, String tid) {
        detailDao.updateSex(sex, tid);
    }

    @Override
    public void updateAderess(String address, String tid) {
        detailDao.updateAderess(address, tid);
    }

}
