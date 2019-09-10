package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.Chatting;

import javax.transaction.Transactional;
import java.util.List;


public interface ChattingDao extends JpaRepository<Chatting, String> {
    @Query(value = "select * from chatting where ch_content like %:content% AND ch_uid = :uid ", nativeQuery = true)
    public List<Chatting> getByIdAndContent(@Param("content") String content, @Param("uid") String uid);

    @Modifying
    @Query(value = "insert into chatting(ch_id,ch_uid,ch_fid,ch_content,ch_time,ch_image,category,ch_name) values (:cid,:uid,:fid,:content,:time1,:image,:category,:cname)", nativeQuery = true)
    public void insterAll(@Param("cid") String cid, @Param("uid") String uid, @Param("fid") String fid,
                          @Param("content") String content, @Param("time1") String time, @Param("image") String image, @Param("category") String category, @Param("cname") String cname);

    @Query(value = "select * from chatting where ch_uid = :id", nativeQuery = true)
    public List<Chatting> getAll(@Param("id") String id);

    @Query(value = "select * from chatting where ch_uid = :fid", nativeQuery = true)
    public List<Chatting> getAllfid(@Param("fid") String fid);

    @Modifying
    @Query(value = "delete from chatting where ch_uid = :uid", nativeQuery = true)
        //根据发送方id删除对应的聊天记录
    void deleteUid(@Param("uid") String uid);

    public List<Chatting> findByChUidOrChFid(String id, String ids);

    @Modifying
    @Query(value = "update chatting set ch_name = ?1 where ch_uid = ?2", nativeQuery = true)
    void updateCname(String name, String uid);

    @Transactional
    @Modifying
    @Query(value = "delete from chatting where ch_uid = :uid and ch_fid = :fid", nativeQuery = true)
    void deleteChatting(@Param("uid") String uid, @Param("fid") String fid); //根据ch_uid和ch_fid进行删除

    @Transactional
    @Modifying
    @Query(value = "update chatting set ch_image = :image where ch_fid = :fid", nativeQuery = true)
    void updateChatting(@Param("image") String image, @Param("fid") String fid);
}
