package we_chat.service;

import we_chat.pojo.Inform;
import we_chat.pojo.NewInform;

import java.util.List;

public interface InformService {
    List<Inform> findByFIdAndFindstate(String fid); //根据f_id和findstate进行查询;条件是findstate要为未查询

    void updateJidState(String fid); //根据fid进行更新查看状态

    List<NewInform> findByJId(String jid); //根据jid进行查询

    void deleteRelationTable(String pid, String bid);
}
