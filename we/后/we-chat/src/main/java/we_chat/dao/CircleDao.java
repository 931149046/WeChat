package we_chat.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.Circle;

import javax.transaction.Transactional;
import java.util.List;

public interface CircleDao extends JpaRepository<Circle, String> {
    @Modifying
    @Query(value = "insert into circle(ci_id, ci_content, ci_address, ci_conimage, ci_time, u_id, j_id, t_id) values (:id,:content,:address,:image,:timee,:uid,:jid,:tid)", nativeQuery = true)
    void inserts(@Param("id") String id, @Param("content") String content, @Param("address") String address, @Param("image") String image, @Param("timee") String time,
                 @Param("uid") String uid, @Param("jid") String jid, @Param("tid") String tid);


    List<Circle> findByUId(String uid);

    @Transactional
    @Modifying
    @Query(value = "delete from circle where u_id = :uid and j_id = :jid", nativeQuery = true)
    void deleteCircle(@Param("uid") String uid, @Param("jid") String jid);

}
