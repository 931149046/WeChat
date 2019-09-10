package we_chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import we_chat.pojo.City;

import javax.transaction.Transactional;
import java.util.List;

public interface CityDao extends JpaRepository<City,Integer> {
    @Query(value = "select * from city where pid = :pid",nativeQuery = true)
    List<City> getCity(@Param("pid") String pid);

    City findById(String id);

}
