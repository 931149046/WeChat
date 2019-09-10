package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.Inform;

import javax.transaction.Transactional;
import java.util.List;


public interface InformDao extends JpaRepository<Inform, String> {
    //添加信息
    @Modifying
    @Query(value = "insert into inform(id, f_id, f_name, content, j_id, state, findstate) values (:id,:fid,:fname,:content,:jid,:state,:findstate)", nativeQuery = true)
    void insertInform(@Param("id") String id, @Param("fid") String fid, @Param("fname") String fname, @Param("content") String content,
                      @Param("jid") String jid, @Param("state") String state, @Param("findstate") String findstate);

    Inform findByFIdAndJId(String fid, String jid); //查询同时满足f_id和j_id的用户才能查询出来

    List<Inform> findByJIdAndFindstate(String fid, String findstate); //根据f_id和findstate进行查询;条件是findstate要为未查询

    @Modifying
    @Query(value = "update inform set findstate = ?1 where j_id = ?2", nativeQuery = true)
        //根据fid进行更新查看状态
    void updateJidState(String findState, String fid);

    List<Inform> findByJId(String jid); //根据jid进行查询

    @Modifying
    @Query(value = "update inform set f_name = ?1 where f_id = ?2", nativeQuery = true)
    void updateFname(String fname, String fid);

    @Transactional
    @Modifying
    @Query(value = "delete from inform where f_id = :fid and j_id = :jid", nativeQuery = true)
    void deleteInform(@Param("fid") String fid, @Param("jid") String jid); //根据fid和jid进行删除
}
