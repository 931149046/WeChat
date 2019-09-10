package we_chat.service;


import org.springframework.data.repository.query.Param;
import we_chat.pojo.Detail;

import java.util.List;

public interface DetailService {
    public List<Detail> getById(String id); //根据t_id查询通讯录详细表对应的数据

    public List<Detail> getByDid1(String id);

    public List<Detail> getByDid(String id);

    Detail findByTId(String tid);

    void updateName(String name, String tid);

    void updateSex(String sex, String tid);

    void updateAderess(String address,String tid);
}
