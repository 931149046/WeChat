package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User, String> {
    @Query(value = "select * from user where u_number = :number", nativeQuery = true)
    public User getNumber(@Param("number") String number);  //根据微信号查询

    @Query(value = "select * from user where u_id = :id", nativeQuery = true)
    public List<User> getById(@Param("id") String id);  //根据id查询

    @Query(value = "select * from user where u_id = :uid", nativeQuery = true)
    public User getByUid(@Param("uid") String uid);  //根据uid查询

    @Query(value = "select * from user where u_number = :number and u_password = :password", nativeQuery = true)
    public User getNumAndPassword(@Param("number") String number, @Param("password") String password);  //根据number和password查询

    @Modifying
    @Query(value = "insert into user(u_id, u_name, u_image, u_number, u_password) values (:id,:name,:image,:number,:password)", nativeQuery = true)
    public void insert(@Param("id") String id, @Param("name") String name, @Param("image") String image, @Param("number") String number, @Param("password") String password);

    @Modifying
    @Query(value = "update user set u_image = :image where u_id = :id", nativeQuery = true)
    void updateImage(@Param("image") String image, @Param("id") String id);

    @Modifying
    @Query(value = "update user set u_name = ?1 where u_id = ?2", nativeQuery = true)
    void updateName(String name, String id);
}
