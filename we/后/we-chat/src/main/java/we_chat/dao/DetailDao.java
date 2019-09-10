package we_chat.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.Detail;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


public interface DetailDao extends JpaRepository<Detail, String> {
    @Query(value = "select * from detail where t_id=:t_id", nativeQuery = true)
    public List<Detail> getById(@Param("t_id") String id);  //根据t_id查询通讯录详细表对应的数据

    @Query(value = "select * from detail where d_id=:d_id", nativeQuery = true)
    public Detail getByDid(@Param("d_id") String id);  //根据d_id查询通讯录详细表对应的数据

    @Query(value = "select * from detail where ci_id=:ci_id", nativeQuery = true)
    public Detail getByCiId(@Param("ci_id") String id);  //根据d_id查询通讯录详细表对应的数据

    @Query(value = "select * from detail where d_id=:d_id", nativeQuery = true)
    public List<Detail> getByDid11(@Param("d_id") String id);  //根据d_id查询通讯录详细表对应的数据

    @Query(value = "select * from detail where t_id=:t_id", nativeQuery = true)
    public List<Detail> getByDid1(@Param("t_id") String id);

    //添加用户详细表的内容
    @Modifying
    @Query(value = "insert into detail(d_id, d_remark, d_number, d_area, d_image, t_id) values (:id,:remark,:number,:area,:image,:tid)", nativeQuery = true)
    void insertDeat(@Param("id") String id, @Param("remark") String remark, @Param("number") String number, @Param("area") String area,
                    @Param("image") String image, @Param("tid") String tid);

    Detail findByTId(String tid);


    @Modifying
    @Query(value = "update detail set d_remark = :name where t_id = :tid", nativeQuery = true)
        //更新用户名
    void updateName(@Param("name") String name, @Param("tid") String tid);

    @Modifying
    @Query(value = "update detail set d_sex = :sex where t_id = :tid", nativeQuery = true)
        //更新性别
    void updateSex(@Param("sex") String sex, @Param("tid") String tid);

    @Modifying
    @Query(value = "update detail set d_area = :address where t_id = :tid", nativeQuery = true)
        //更新地名
    void updateAderess(@Param("address") String address, @Param("tid") String tid);

    @Transactional
    @Modifying
    @Query(value = "update detail set d_image = :image where t_id = :tid", nativeQuery = true)
    void updateDetail(@Param("image") String image,@Param("tid") String tid);
}
