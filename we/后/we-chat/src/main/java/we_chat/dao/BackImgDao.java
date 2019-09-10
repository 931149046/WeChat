package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import we_chat.pojo.Backimage;

import javax.transaction.Transactional;
import java.util.List;

public interface BackImgDao extends JpaRepository<Backimage, String> {
    @Query(value = "select * from backimage where u_id = ?1", nativeQuery = true)
    public List<Backimage> getById(String uid);  //根据用户名的id查询背景图
}
