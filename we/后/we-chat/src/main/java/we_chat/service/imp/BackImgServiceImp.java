package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.BackImgDao;
import we_chat.pojo.Backimage;
import we_chat.service.BackImgService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class BackImgServiceImp implements BackImgService {
    @Autowired
    public BackImgDao backImgDao;
    @Override
    public List<Backimage> getById(String uid) { //根据用户名的id查询背景图
        return backImgDao.getById(uid);
    }
}
