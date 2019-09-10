package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.The;

import javax.transaction.Transactional;
import java.lang.invoke.VarHandle;
import java.util.List;


public interface TheDao extends JpaRepository<The, String> {
    @Query(value = "select * from the order by t_letter ASC", nativeQuery = true)
    public List<The> getAll(); //查询通讯录表所有数据

    @Query(value = "select * from the where u_id = :uid order by t_letter ASC", nativeQuery = true)
    public List<The> getAllById(@Param("uid") String uid); //根据用户id进行查询对应当前用户的好友

    @Modifying
    @Query(value = "insert into the(t_id, t_name, t_porimage, t_letter, u_id,ud_id) values (:id,:name,:porimage,:letter,:uid,:udid)", nativeQuery = true)
    void insert(@Param("id") String id, @Param("name") String name, @Param("porimage") String porimage,
                @Param("letter") String letter, @Param("uid") String uid, @Param("udid") String udid);

    @Query(value = "from The where tName = :name")
    public The getNumber(@Param("name") String name);

    The findByUIdAndUdId(String jid, String fid); //根据uid和udid同时查询,查询到有数据就不进行添加了

    @Query(value = "select * from the where ud_id = :fid and u_id =:jid", nativeQuery = true)
    The findByUdIdAndUId(@Param("fid") String fid, @Param("jid") String jid);

    List<The> findByUdId(String uid);

    @Modifying
    @Query(value = "update the set  ci_id = :cid where u_id = :uid and ud_id = :udid", nativeQuery = true)
    void insertCid(@Param("cid") String cid, @Param("uid") String uid, @Param("udid") String udid);

    @Query(value = "select * from the where ci_id = ?1", nativeQuery = true)
    The getByciId(String cid);

    @Modifying
    @Query(value = "update the set t_name = ?1 where ud_id = ?2", nativeQuery = true)
    void updateTname(String tname, String udid);

    @Transactional
    @Modifying
    @Query(value = "delete from the where u_id = :uid and ud_id = :udid", nativeQuery = true)
    void deleteThe(@Param("uid") String uid, @Param("udid") String udid); //根据uid和udid进行删除

    @Transactional
    @Modifying
    @Query(value = "update the set t_porimage = :image where ud_id = :udid",nativeQuery = true)
    void updateThe(@Param("image") String image,@Param("udid") String udid);
}
