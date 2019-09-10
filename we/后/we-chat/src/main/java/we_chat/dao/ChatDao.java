package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.Chat;
import we_chat.pojo.The;

import javax.transaction.Transactional;
import java.util.List;


public interface ChatDao extends JpaRepository<Chat, String> {
    @Modifying
    @Query(value = "insert into chat(c_id,d_Remark,c_porimage,c_time,d_idpk,u_id) values (:cid,:name,:porimage,:time,:did,:uid)", nativeQuery = true)
    public void install(@Param("cid") String id, @Param("name") String name, @Param("porimage") String porimage,
                        @Param("time") String time, @Param("did") String did, @Param("uid") String uid);

    @Query(value = "select * from chat where d_idpk=?1", nativeQuery = true)
    public List<Chat> getById(String id);

    @Query(value = "select * from chat where d_idpk=?1 and u_id = ?2", nativeQuery = true)
    public List<Chat> getByIdAndUid(String id, String uid);

    @Query(value = "select * from chat where u_id = :uid", nativeQuery = true)
    public List<Chat> getAllById(@Param("uid") String uid); //根据当前用户id查询对应哪些聊天的人

    @Modifying
    @Query(value = "delete from chat where c_id = :id", nativeQuery = true)
    public void deleteById(@Param("id") String id);

    @Query(value = "select * from chat where d_idpk=?1", nativeQuery = true)
    List<Chat> getBydidpkId(String id);

    @Modifying
    @Query(value = "update chat set d_Remark = ?1 where d_idpk = ?2", nativeQuery = true)
    void updateRname(String rname, String did);

    @Transactional
    @Modifying
    @Query(value = "delete from chat where d_idpk = :didpk", nativeQuery = true)
    void deleteChat(@Param("didpk") String didpk);

    @Transactional
    @Modifying
    @Query(value = "update chat set c_porimage = :image where d_idpk = :didpk", nativeQuery = true)
    void updateChat(@Param("image") String image,@Param("didpk") String didpk);
}
