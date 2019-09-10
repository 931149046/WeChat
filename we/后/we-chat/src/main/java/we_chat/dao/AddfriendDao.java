package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.Addfriend;

import javax.transaction.Transactional;
import java.util.List;

public interface AddfriendDao extends JpaRepository<Addfriend, String> {
    @Modifying  //添加等待对方接收的信息
    @Query(value = "insert into addfriend(id, f_id, f_name, content, j_id, state,findstate) values (:id,:fid,:fname,:content,:jid,:state,:findstate)", nativeQuery = true)
    void insert(@Param("id") String id, @Param("fid") String fid, @Param("fname") String fname, @Param("content") String content,
                @Param("jid") String jid, @Param("state") String state, @Param("findstate") String findstate);

    @Query(value = "select * from addfriend where j_id = :jid and findstate = :findstate", nativeQuery = true)
    List<Addfriend> getjidAndState(@Param("jid") String jid, @Param("findstate") String findstate);  //查询等待用户验证表中有没有需要进行等待处理的表

    @Modifying
    @Query(value = "update addfriend set findstate = :findstate where j_id = :jid", nativeQuery = true)
        //更新是否查看状态
    void updateFindstate(@Param("jid") String jid, @Param("findstate") String finstate);

    @Query(value = "select * from addfriend where j_id = :jid and findstate = :findstate", nativeQuery = true)
    List<Addfriend> getjidAndStateAndImage(@Param("jid") String jid,@Param("findstate") String findstate); //根据接收者id查询

    @Modifying
    @Query(value = "update addfriend set state = ?1 where j_id = ?2", nativeQuery = true)
        //根据接收人id进行更新是否同意的状态
    void updateStateWhereJId(String state, String jid);

    //接收人id和发送人id同时存在的话就不再添加了
    Addfriend findByFIdAndJId(String fid, String jid);

    List<Addfriend> findByFId(String fid); //根据发送者id进行查询

    @Modifying
    @Query(value = "update addfriend set f_name = :fname where f_id = :fid", nativeQuery = true)
    void updateFname(@Param("fname") String fname, @Param("fid") String fid);

    @Transactional
    @Modifying
    @Query(value = "delete from addfriend where f_id = :fid and j_id = :jid", nativeQuery = true)
    void deleteAddfriend(@Param("fid") String fid, @Param("jid") String jid);

    @Modifying
    @Query(value = "update addfriend set findstate = :findstate where f_id = :fid and j_id = :jid",nativeQuery = true)
    void updateAddfriendFindState(String findstate,String fid,String jid);
}
